package oa.wx.util;

import oa.wx.Mapper.LoLVideoMapper;
import oa.wx.Service.LolVideoService;
import oa.wx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class setTimer {
    @Autowired
    UserService userService;
    @Autowired
    LolVideoService lolVideoService;
    /**
     *@author CtrlCver
     *@data 2022/11/3
     *@description:  定时刷新数据库token
     */
    @Scheduled(fixedRate = 7100000)
    public void updateToken(){
       String token= AccessToken.sendGet();
        System.out.println(token);
       int a=userService.UpdateToken(token);
        System.out.println(a);
    }
    /**
     *@author CtrlCver
     *@data 2023/2/26
     *@description: 此后处原来为上传video类型，但是尝试过一次发现上传视频在公众号那边一直加载无法观看，所以废除
     */
//    @Scheduled(fixedRate = 86400000)
//    public void updateVideo(){
//        List<String> names=lolVideoService.GetAllName();
//        System.out.println(names);
//        for (String name : names) {
//            String mediaid=UpVideo.upload(name,"video",userService.findToken());
//            lolVideoService.UpdateMediaIdByName(name,mediaid);
//            System.out.println(mediaid);
//        }
//    }
}
