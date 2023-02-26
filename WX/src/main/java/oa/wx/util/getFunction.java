package oa.wx.util;

import oa.wx.Entity.ImageMessage;
import oa.wx.Entity.TextMessage;

import java.util.Date;
import java.util.Map;

public  class getFunction {
    public static String GuideSign(Map<String, String> map){
        Date data=new Date();
        TextMessage txtmsg = new TextMessage();
        txtmsg.setToUserName(map.get("FromUserName"));
        txtmsg.setFromUserName(map.get("ToUserName"));
        txtmsg.setCreateTime(data.getTime());
        txtmsg.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_TEXT);
        txtmsg.setContent("回复数字获取对应功能：           "+
                "1:每日一言                     "+
                "2:LOL海报                     "+
                "3:LOL台词                     "+
                "4:RNG                        ");
        return MsgTypeAndXml.textMessageToXml(txtmsg);
    }
}
