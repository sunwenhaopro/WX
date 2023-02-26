package oa.wx.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoLVideoMapper {
    int UpdateMediaIdByName(String name,String mediaid);
    String GetMediaIdByName(String name);
    String GetVideoUrlByName(String name);
    List<String> GetAllName();
}
