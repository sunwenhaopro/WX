package oa.wx.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LolLinesMapper {
    int getAllLines();
    String getRandomLine(int id);
    int insertLolLine(String line);
}
