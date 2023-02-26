package oa.wx.Service.Impl;

import oa.wx.Mapper.LolLinesMapper;
import oa.wx.Service.LolLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LolLinesServiceImpl implements LolLinesService {
    @Autowired
    LolLinesMapper lolLinesMapper;
    public int getAllLines(){return lolLinesMapper.getAllLines();}
    public String getRandomLine(int id){
        return lolLinesMapper.getRandomLine(id);
   }
   public int insertLolLine(String lolline){return lolLinesMapper.insertLolLine(lolline);}

}
