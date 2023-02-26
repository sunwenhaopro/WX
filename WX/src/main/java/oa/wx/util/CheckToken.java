package oa.wx.util;


import java.util.Arrays;

public class CheckToken {
    private static final String token="swhcyx123456";
    public static boolean checkSignture(String signture, String timestamp, String nonce){
      String[] str=new String[]{token,timestamp,nonce};
      Arrays.sort(str);
      StringBuffer buffer=new StringBuffer();
      for(int i=0;i<str.length;i++){
          buffer.append(str[i]);
      }
      String temp=SHA1.encode(buffer.toString());
      return signture.equals(temp);
    }
}
