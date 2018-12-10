package com.jwebcoder.grocerymain.photography.service;

import com.jwebcoder.grocerymain.photography.entity.PhotographyDetail;

public interface PhotographyService {

    PhotographyDetail getPhotographyDetailById(String id);

    void savePhoto(String photo);

}
