package oa.wx.Service.Impl;

import oa.wx.Mapper.LoLVideoMapper;
import oa.wx.Service.LolLinesService;
import oa.wx.Service.LolVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LolVideoServiceImpl implements LolVideoService{
    @Autowired
    LoLVideoMapper loLVideoMapper;

    public int UpdateMediaIdByName(String name, String mediaid) {
        return loLVideoMapper.UpdateMediaIdByName(name, mediaid);
    }
public String GetMediaUrlByName(String name){return loLVideoMapper.GetVideoUrlByName(name);}
public List<String> GetAllName(){return loLVideoMapper.GetAllName();}
    public String GetMediaIdByName(String name) {
        return loLVideoMapper.GetMediaIdByName(name);
    }
}