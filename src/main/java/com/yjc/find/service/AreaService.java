package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.pojo.Area;
import org.springframework.stereotype.Service;

@Service
public interface AreaService extends IService<Area> {

    void saveArea(Area area);

    void updateArea(Area area);
}
