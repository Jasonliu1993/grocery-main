package com.jwebcoder.grocerymain.common.repository;


import com.jwebcoder.grocerymain.common.entity.PersonalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, String> {
}
