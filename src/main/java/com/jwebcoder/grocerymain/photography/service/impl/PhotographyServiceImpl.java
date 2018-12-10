package com.jwebcoder.grocerymain.photography.service.impl;

import com.jwebcoder.grocerymain.common.utils.DateUtility;
import com.jwebcoder.grocerymain.photography.constants.PhotographyContst;
import com.jwebcoder.grocerymain.photography.entity.PhotographyDetail;
import com.jwebcoder.grocerymain.photography.entity.PhotographyPhoto;
import com.jwebcoder.grocerymain.photography.repository.PhotographyDetailRepository;
import com.jwebcoder.grocerymain.photography.repository.PhotographyPhotoRepository;
import com.jwebcoder.grocerymain.photography.service.PhotographyService;
import com.jwebcoder.grocerymain.photography.utils.PhotoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class PhotographyServiceImpl implements PhotographyService {

    private static final Logger logger = LoggerFactory.getLogger(PhotographyServiceImpl.class);

    @Autowired
    private PhotographyDetailRepository photographyDetailRepository;

    @Autowired
    private PhotographyPhotoRepository photographyPhotoRepository;

    @Override
    public PhotographyDetail getPhotographyDetailById(String id) {
        Optional<PhotographyDetail> photographyDetail = photographyDetailRepository.findById(id);
        if (photographyDetail.isPresent()) {
            return photographyDetail.get();
        }
        return null;
    }

    @Override
    @Transactional
    public void savePhoto(String photo) {
        PhotographyPhoto photographyPhoto = new PhotographyPhoto();
        photographyPhoto.setType(PhotographyContst.PHOTO_TYPE);
        photographyPhoto.setDesc("图片分享");
        photographyPhoto.setVersion(0);
        String photoPath = PhotographyContst.PHOTO_FILE + "/" + PhotoUtil.getUUID() + ".jpg";
        logger.info("PhotographyServiceImpl/savePhoto/photoPath{}", photoPath);
        photographyPhoto.setPath(photoPath);

        photographyPhotoRepository.save(photographyPhoto);

        PhotographyDetail photographyDetail = new PhotographyDetail();

        photographyDetail.setVersion(0);
        photographyDetail.setTheme("这是一个测试主题");
        photographyDetail.setComments("这是一个测试主题");
        photographyDetail.setCreateDatetime(DateUtility.getCurrentDate());
//        photographyDetail.setPhotoRefId(photographyPhoto.getId());
        photographyDetail.setPhotographyPhoto(photographyPhoto);

        photographyDetailRepository.save(photographyDetail);

        File photoFile = new File(photoPath);

        try (FileOutputStream fileWriter = new FileOutputStream(photoFile)) {

            byte[] bytes = PhotoUtil.decryptPhoto(photo);
            fileWriter.write(bytes);
            fileWriter.flush();

        } catch (IOException e) {
            logger.error("PhotographyServiceImpl/savePhoto/savePhoto has error", e);
            throw new RuntimeException("保存图片失败");
        }

    }
}
