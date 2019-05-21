package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.pojo.School;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SchoolService extends IService<School> {
    void saveSchool(School school, MultipartFile shopImg);

    void updateSchool(School school, MultipartFile schoolImg);

    void removeSchool(Long id);
}
