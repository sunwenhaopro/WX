package oa.wx.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface LolVideoService {
    int UpdateMediaIdByName(String name,String mediaid);
    String GetMediaIdByName(String name);
    String GetMediaUrlByName(String name);
    List<String> GetAllName();
}
