package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.exception.MyException;
import com.yjc.find.dao.AreaMapper;
import com.yjc.find.dao.SchoolMapper;
import com.yjc.find.pojo.Area;
import com.yjc.find.pojo.School;
import com.yjc.find.service.AreaService;
import com.yjc.find.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public void saveArea(Area area) {
        MyUtil.checkNull(area);
        MyUtil.checkNull(area.getSchoolId());
        if (schoolMapper.selectById(area.getSchoolId())==null){
            MyUtil.checkFailed();
        }
        MyUtil.checkNull(area.getAreaName());
        Area queryArea = new Area();
        queryArea.setAreaName(area.getAreaName()).setSchoolId(area.getSchoolId());
        //存在同名地区
        if (areaMapper.selectOne(new QueryWrapper<>(queryArea))!=null){
            MyUtil.checkFailed("地区名重复");
        }
        areaMapper.insert(area);
    }

    @Override
    public void updateArea(Area area) {
        MyUtil.checkNull(area);
        MyUtil.checkNull(area.getAreaName());
        MyUtil.checkNull(area.getSchoolId());
        Area queryArea = new Area();
        queryArea.setAreaName(area.getAreaName()).setSchoolId(area.getSchoolId());
        if (areaMapper.selectOne(new QueryWrapper<>(queryArea))!=null){
            MyUtil.checkFailed("地区名重复");
        }
        areaMapper.updateById(queryArea);
    }
}
