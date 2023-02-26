package oa.wx.Mapper;


import oa.wx.Entity.AccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {

    int UpdateToken(String token);
    String findToken();
    int insertMediaId(String mediaid);
    String findMediaId();

}
