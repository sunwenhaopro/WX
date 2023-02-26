package oa.wx.util;

import net.sf.json.JSONObject;
import oa.wx.Service.LolVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Random;
@Component
public class UpVideo {
    @Autowired
    LolVideoService lolVideoService;
    @Autowired
    static LolVideoService lolVideoService1;
    @PostConstruct
    public void init(){
        lolVideoService1=lolVideoService;
    }
    public static String upload(String name,String fileType,String Token) {
        String urlList = lolVideoService1.GetMediaUrlByName(name);
        Random r=new Random();
        String result=null;
        String mediaId=null;
        try {
            URL imgurl=new URL(urlList);
            String  token=Token;
            String urlString="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE".replace("ACCESS_TOKEN", token).replace("TYPE", fileType);
            URL url=new URL(urlString);
            HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            //设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            //设置边界
            String BOUNDARY="----------"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            //请求正文信息
            //第一部分
            StringBuilder sb=new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"media\"; filename=\"" + r.nextInt(2000)+".mp4"+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            //获得输出流
            OutputStream out=new DataOutputStream(conn.getOutputStream());
            //输出表头
            out.write(sb.toString().getBytes("UTF-8"));
            //文件正文部分
            //把文件以流的方式 推送道URL中
            DataInputStream din=new DataInputStream(imgurl.openStream());
            int bytes=0;
            byte[] buffer=new byte[1024];
            while((bytes=din.read(buffer))!=-1){
                out.write(buffer,0,bytes);
            }
            din.close();
            //结尾部分
            byte[] foot=("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");//定义数据最后分割线
            out.write(foot);
            out.flush();
            out.close();
            if(HttpsURLConnection.HTTP_OK==conn.getResponseCode()){
                StringBuffer strbuffer=null;
                BufferedReader reader=null;
                try {
                    strbuffer=new StringBuffer();
                    reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String lineString=null;
                    while((lineString=reader.readLine())!=null){
                        strbuffer.append(lineString);
                    }
                    if(result==null){
                        result=strbuffer.toString();
                        JSONObject jsonObj = JSONObject.fromObject(result);
                        String typeName = "media_id";
                        if(!"video".equals(fileType)){
                            typeName = fileType + "_media_id";
                        }
                        System.out.println(jsonObj);
                        mediaId = jsonObj.getString(typeName);
                    }
                } catch (IOException e) {
                    System.out.println("发送POST请求出现异常！"+e);
                    e.printStackTrace();
                }finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mediaId);
        return mediaId;
    }
}
