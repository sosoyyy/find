package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.exception.MyException;
import com.yjc.find.dao.SchoolMapper;
import com.yjc.find.pojo.School;
import com.yjc.find.service.SchoolService;
import com.yjc.find.utils.ImageUtil;
import com.yjc.find.utils.MyUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Override
    public void saveSchool(School school, MultipartFile shopImg) {
        //保存图片
        try {
            if (shopImg != null){
            String imgAddr = ImageUtil.storeImg(shopImg.getInputStream(), shopImg.getOriginalFilename());
            school.setSchoolImgAddr(imgAddr);
            }
        }catch (IOException e){
            throw new MyException("图片处理出错");
        }
        MyUtil.checkNull(school);
        MyUtil.checkNull(school.getSchoolName());
        School querySchool = new School();
        querySchool.setSchoolName(school.getSchoolName());
        if (this.baseMapper.selectOne(new QueryWrapper<>(querySchool))!=null){
            MyUtil.checkFailed();
        }
        this.baseMapper.insert(school);
    }

    @Override
    public void updateSchool(School school, MultipartFile schoolImg) {
        MyUtil.checkNull(school);
        MyUtil.checkNull(school.getId());
        if (schoolImg!=null){
        String oldAddr = this.baseMapper.selectById(school.getId()).getSchoolImgAddr();
        ImageUtil.deleteFileOrPath(oldAddr);
        String schoolImgAddr = null;
        try {
           schoolImgAddr = ImageUtil.storeImg(schoolImg.getInputStream(),schoolImg.getOriginalFilename());
        }catch (IOException e){
            throw new MyException("图片处理出错");
        }
        school.setSchoolImgAddr(schoolImgAddr);
        School querySchool = new School();
        querySchool.setSchoolName(school.getSchoolName());
        if (this.baseMapper.selectOne(new QueryWrapper<>(querySchool))!=null){
            MyUtil.checkFailed();
        }
        this.baseMapper.updateById(school);
        }
    }

    @Override
    public void removeSchool(Long id) {
        MyUtil.checkNull(id);
        School school = this.baseMapper.selectById(id);
        MyUtil.checkNull(school);
        if(school.getSchoolImgAddr()!=null) {
            ImageUtil.deleteFileOrPath(school.getSchoolImgAddr());
        }
        this.baseMapper.deleteById(id);
    }

    @Override
    public MyPage<School> getPage(MyPage<School> page) {
        page.setRecords(this.baseMapper.selectSchoolPage(page.getParams()));
        page.setCount(this.baseMapper.selectSchoolCount(page.getParams()));
        return page;
    }
}
