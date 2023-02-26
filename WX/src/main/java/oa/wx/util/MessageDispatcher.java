package oa.wx.util;

import oa.wx.Entity.Image;
import oa.wx.Entity.ImageMessage;
import oa.wx.Entity.TextMessage;

import oa.wx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Date;

import java.util.Map;
@Component
public class MessageDispatcher {
    @Autowired
    UserService userService;
    @Autowired
    static UserService userService1;
    @PostConstruct
    public void init(){
        userService1=userService;
    }

    public static String processMessage(Map<String, String> map) throws InterruptedException {
           if (Integer.parseInt(map.get("Content"))==0) {
              return getFunction.GuideSign(map);
           } else if (Integer.parseInt(map.get("Content"))==1) {
              return getRandomSay.RandomSay(map);
           }else if(Integer.parseInt(map.get("Content"))==2) {
               return getRandomLoLPhoto.RandomLoLPhoto(map);
           }else if(Integer.parseInt(map.get("Content"))==3) {
               return getRandomLolLine.RandomLoLLine(map);
           }else if (Integer.parseInt(map.get("Content"))==4){
             return getLOLVideo.LoLVideo(map);
           }else {
               return  getFunction.GuideSign(map);
           }
    }
    public static String processEvent(Map<String, String> map) {
        Date data = new Date();
        String openid = map.get("FromUserName");
        String mpid = map.get("ToUserName");
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(openid);
        txtmsg.setFromUserName(mpid);
        txtmsg.setCreateTime(data.getTime());
        if(MsgTypeAndXml.EVENT_TYPE_SUBSCRIBE.equals(map.get("Event"))) {
           txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
           txtmsg.setContent("欢迎订阅CtrlCVer！本公众号为个人博客https://www.ctrlcver.icu站点公众号。了解更多功能请回复0！");
           return MsgTypeAndXml.textMessageToXml(txtmsg);
        } else if (MsgTypeAndXml.EVENT_TYPE_UNSUBSCRIBE.equals(map.get("Event"))) {
           return null;
        }else {
           txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
           txtmsg.setContent("emmmmmmmmm");
           return MsgTypeAndXml.textMessageToXml(txtmsg);
        }
    }
}


