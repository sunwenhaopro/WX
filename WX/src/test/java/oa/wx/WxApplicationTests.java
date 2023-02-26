package oa.wx;


import oa.wx.Mapper.LolLinesMapper;
import oa.wx.Service.LolLinesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;


@SpringBootTest
class WxApplicationTests {
    LolLinesService lolLinesService;
    @Test
    void contextLoads() {
    }

@Test
    public void test1() throws IOException {
    String fileName = "D:/lol.txt";
    File file = new File(fileName);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    String line;
    while((line = br.readLine()) != null){
        System.out.println(line+"1");
    }



}
}
