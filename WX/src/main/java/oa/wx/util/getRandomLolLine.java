package oa.wx.util;

import oa.wx.Entity.TextMessage;
import oa.wx.Mapper.LolLinesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.Random;
@Component
public class getRandomLolLine {
    @Autowired
    LolLinesMapper lolLinesMapper;
    @Autowired
    static  LolLinesMapper lolLinesMapper1;
    @PostConstruct
    public void init(){
       lolLinesMapper1=lolLinesMapper;
    }
    public static String RandomLoLLine(Map<String, String> map){
        int maxid=lolLinesMapper1.getAllLines();
        System.out.println(maxid);
        Random random = new Random();
        int randomid = random.nextInt(maxid);
        System.out.println(randomid);
        Date data=new Date();
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(map.get("FromUserName"));
        txtmsg.setFromUserName(map.get("ToUserName"));
        txtmsg.setCreateTime(data.getTime());
        txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
        txtmsg.setContent(lolLinesMapper1.getRandomLine(randomid));
        System.out.println(lolLinesMapper1.getRandomLine(randomid));
        return MsgTypeAndXml.textMessageToXml(txtmsg);
    }
}
