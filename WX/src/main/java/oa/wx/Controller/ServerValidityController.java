package oa.wx.Controller;

import oa.wx.Service.LolLinesService;
import oa.wx.Service.UserService;
import oa.wx.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerValidityController {
    /**
     *@author CtrlCver
     *@data 2022/11/3
     *@description: 验证token
     */
    @GetMapping("/wxx")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String signature=httpServletRequest.getParameter("signature");
        String timestamp=httpServletRequest.getParameter("timestamp");
        String nonce=httpServletRequest.getParameter("nonce");
        String echostr=httpServletRequest.getParameter("echostr");
        PrintWriter out=null;
        try{
            out =httpServletResponse.getWriter();
            if(CheckToken.checkSignture(signature,timestamp,nonce)){
               out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
    @PostMapping(value = "/wxx",produces = "application/xml;charset=utf-8")
    public String handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Map<String, String> map = MsgTypeAndXml.parseXml(request);
        String msgType = map.get("MsgType");
        if(MsgTypeAndXml.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
            return MessageDispatcher.processEvent(map);
        } else {
            return MessageDispatcher.processMessage(map);
        }
    }
}

