package oa.wx.util;

import oa.wx.Entity.*;
import oa.wx.Service.LolVideoService;
import oa.wx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

@Component
public class getLOLVideo {
    @Autowired
    LolVideoService lolVideoService;
    @Autowired
    static LolVideoService lolVideoService1;
    @PostConstruct
    public void init(){
        lolVideoService1=lolVideoService;
    }
    public static String LoLVideo(Map<String, String> map) throws InterruptedException {
//        Date data=new Date();
//        VideoMessage videoMessage = new VideoMessage();
//        videoMessage.setToUserName(map.get("FromUserName"));
//        videoMessage.setFromUserName(map.get("ToUserName"));
//        videoMessage.setCreateTime(data.getTime());
//        videoMessage.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_Video);
//        videoMessage.setVideo(new Video(lolVideoService1.GetMediaIdByName("小虎"),
//                map.get("Content"),
//                map.get("Content") ));
//        System.out.println(MsgTypeAndXml.videoMessageToXml(videoMessage));
//        return MsgTypeAndXml.videoMessageToXml(videoMessage);
        Date data=new Date();
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(map.get("FromUserName"));
        txtmsg.setFromUserName(map.get("ToUserName"));
        txtmsg.setCreateTime(data.getTime());
        txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
        txtmsg.setContent("1.小虎"+lolVideoService1.GetMediaUrlByName("小虎"));
        return MsgTypeAndXml.textMessageToXml(txtmsg);
    }
}
