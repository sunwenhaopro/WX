package oa.wx.Service.Impl;

import oa.wx.Entity.AccessToken;
import oa.wx.Mapper.UserMapper;
import oa.wx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    public int insertMediaId(String mediaid){return userMapper.insertMediaId(mediaid);}
    public  int UpdateToken(String token){return userMapper.UpdateToken(token);}

   public String findToken(){return userMapper.findToken();}
    public String findMediaId(){return userMapper.findMediaId();}

}
