package com.jwebcoder.grocerymain.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Transient;
import java.util.List;


public class AdminAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationProvider.class);

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AdminAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        AdminAuthenticationToken token = (AdminAuthenticationToken) authentication;
        String username = token.getUsername();
        String password = token.getCredentials().toString();
        UserDetails loadedUser = null;
        try {
//            loadedUser = new UserDetailsImpl(username, password, this.getGrantedAuthorityList());
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(
                    repositoryProblem.getMessage(), repositoryProblem);
        }
        if (loadedUser == null) {
            logger.error("UserDetailsService returned null, which is an interface contract violation.");
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        try {
            additionalAuthenticationChecks(loadedUser, token);
        } catch (AuthenticationException exception) {
            logger.error("Username and password are invalid.", exception);
            throw exception;
        }
        token.setPrincipal(loadedUser);
        token.setDetails(loadedUser);
        token.setAuthorities(loadedUser.getAuthorities());
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails, AdminAuthenticationToken authentication)
            throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String authPassword = authentication.getCredentials().toString();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentedPassword = passwordEncoder.encode(authPassword);
        if (!passwordEncoder.matches(userDetails.getPassword(), presentedPassword)) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Transient
    public List<GrantedAuthority> getGrantedAuthorityList() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }


}