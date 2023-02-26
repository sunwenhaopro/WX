package xyz.luyie.weather.vo;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  10:57
 *  预测未来天气
 */
public class Forecast {
    private String date;

    private String high;

    private String fengli;

    private String low;

    private String fengxiang;

    private String type;

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setHigh(String high){
        this.high = high;
    }
    public String getHigh(){
        return this.high;
    }
    public void setFengli(String fengli){
        this.fengli = fengli;
    }
    public String getFengli(){
        return this.fengli;
    }
    public void setLow(String low){
        this.low = low;
    }
    public String getLow(){
        return this.low;
    }
    public void setFengxiang(String fengxiang){
        this.fengxiang = fengxiang;
    }
    public String getFengxiang(){
        return this.fengxiang;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
