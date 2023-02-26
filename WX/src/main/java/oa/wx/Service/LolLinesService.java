package oa.wx.Service;

public interface LolLinesService {
    int getAllLines();
    String getRandomLine(int id);
    int insertLolLine(String lolline);

}
