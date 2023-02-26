package oa.wx.Service;

import oa.wx.Entity.AccessToken;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    int UpdateToken(String token);
    String findToken();
    int insertMediaId(String mediaid);
   String findMediaId();
}
