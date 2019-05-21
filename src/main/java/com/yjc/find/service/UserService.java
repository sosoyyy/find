package com.yjc.find.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService extends IService<User> {
    void update(User user, MultipartFile userImg);

    User getUser(Long id);

    MyPage<User> getUserPage(MyPage<User> page);
}
