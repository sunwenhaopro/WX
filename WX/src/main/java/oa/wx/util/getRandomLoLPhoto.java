package oa.wx.util;

import oa.wx.Entity.Image;
import oa.wx.Entity.ImageMessage;
import oa.wx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
@Component
public class getRandomLoLPhoto {
    @Autowired
    UserService userService;
    @Autowired
    static UserService userService1;
    @PostConstruct
    public void init(){
        userService1=userService;
    }
    public static String RandomLoLPhoto(Map<String, String> map) throws InterruptedException {
        Date data=new Date();
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(map.get("FromUserName"));
        imageMessage.setFromUserName(map.get("ToUserName"));
        imageMessage.setCreateTime(data.getTime());
        imageMessage.setMsgType(MsgTypeAndXml.RESP_MESSAGE_TYPE_Image);
        /**
         *@author CtrlCver
         *@data 2022/11/2
         *@description:    腾讯微信端口必须五秒内响应。
         * 服务器太垃圾了，使用多线程解决。服务器是单核的本来运行多线程效果提升一般，但是我insertmediaid是一个io流操作，正好cpu
         * 给线程1用。        原来是即时获取mediaid，现在使用的是交错进行，即这次图片请求发送的是上次请求返回的mediaid。
         * 先返回信息给给服务端，然后把耗时的对到后端慢漫运行。秒！！
         */
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                imageMessage.setImage(new Image(userService1.findMediaId()));
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                userService1.insertMediaId(UpImage.upload("image", userService1.findToken()));
            }
        });
        threadOne.start();
        threadTwo.start();
        Thread.sleep(1000);
        return MsgTypeAndXml.imageMessageToXml(imageMessage);
    }
}
