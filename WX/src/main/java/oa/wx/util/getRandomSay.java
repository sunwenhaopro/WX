package oa.wx.util;

import oa.wx.Entity.TextMessage;

import java.util.Date;
import java.util.Map;

public class getRandomSay {
    public static String RandomSay(Map<String, String> map){
        Date data=new Date();
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(map.get("FromUserName"));
        txtmsg.setFromUserName(map.get("ToUserName"));
        txtmsg.setCreateTime(data.getTime());
        txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
        txtmsg.setContent(RandomSay.sendGet("https://v1.hitokoto.cn/?encode=text&c=a", "utf-8"));
        return MsgTypeAndXml.textMessageToXml(txtmsg);
    }
}
