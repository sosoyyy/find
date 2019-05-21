package com.yjc.find.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.find.base.bean.MyPage;
import com.yjc.find.base.exception.MyException;
import com.yjc.find.dao.UserMapper;
import com.yjc.find.pojo.User;
import com.yjc.find.service.UserService;
import com.yjc.find.utils.ImageUtil;
import com.yjc.find.utils.MyUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    @Override
    public void update(User user, MultipartFile userImg) {
        MyUtil.checkNull(user);
        MyUtil.checkNull(user.getId());
        user.setPassword(null);
        if (userImg!=null){
            String img =  this.baseMapper.selectById(user.getId()).getUserImgAddr();
            try {
                if (img != null) {
                    ImageUtil.deleteFileOrPath(img);
                }
                String newImg = ImageUtil.storeImg(userImg.getInputStream(), userImg.getOriginalFilename());
                user.setUserImgAddr(newImg);
            }catch (Exception e){
                throw new MyException("图片处理错误");
            }
        }
        this.baseMapper.insert(user);
    }

    @Override
    public User getUser(Long id) {
        return this.baseMapper.selectUser(id);
    }

    @Override
    public MyPage<User> getUserPage(MyPage<User> page) {
        page.setCount(this.baseMapper.selectUserCount(page.getParams()));
        page.setRecords(this.baseMapper.selectUserPage(page.getParams()));
        return page;
    }
}
