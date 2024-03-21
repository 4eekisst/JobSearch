package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.UserImageDao;
import com.example.jobsearch.dto.UserImageDto;
import com.example.jobsearch.models.UserImage;
import com.example.jobsearch.services.UserImageService;
import com.example.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserImageServiceImpl implements UserImageService {

    private final UserImageDao userImageDao;

    private final FileUtil fileUtil;

    @Override
    public void upload(UserImageDto userImageDto) {
        String fileName = fileUtil.saveUploadedFile(userImageDto.getFile(), "images");
        UserImage userImage = new UserImage();
        userImage.setUserId(userImageDto.getUserId());
        userImage.setFileName(fileName);

        userImageDao.save(userImage);
    }
}
