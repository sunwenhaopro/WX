package xyz.luyie.weather.vo;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  10:59
 *  最外层响应参数
 */
public class ResponseResult {
    private Data data;

    private int status;

    private String desc;

    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
}
