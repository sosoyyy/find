package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.Boards;
import org.springframework.stereotype.Service;

@Service
public interface BoardsService extends IService<Boards> {
    MyPage<Boards> getBoardsPage(MyPage<Boards> page);
}
