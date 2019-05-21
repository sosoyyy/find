package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.dao.BoardsMapper;
import com.yjc.find.pojo.Boards;
import com.yjc.find.service.BoardsService;
import org.springframework.stereotype.Service;

@Service
public class BoardsServiceImpl extends ServiceImpl<BoardsMapper, Boards> implements BoardsService {
    @Override
    public MyPage<Boards> getBoardsPage(MyPage<Boards> page) {
        page.setRecords(this.baseMapper.selectBoardsPage(page.getParams()));
        page.setCount(this.baseMapper.selectBoardsCount(page.getParams()));
        return page;
    }
}
