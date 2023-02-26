package xyz.luyie.weather.vo;

/**
 * Author:  陆袆 >_<
 * Time: 2020-08-14  10:56
 *  昨日天气
 */
public class Yesterday {
    private String date;

    private String high;

    private String fx;

    private String low;

    private String fl;

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
    public void setFx(String fx){
        this.fx = fx;
    }
    public String getFx(){
        return this.fx;
    }
    public void setLow(String low){
        this.low = low;
    }
    public String getLow(){
        return this.low;
    }
    public void setFl(String fl){
        this.fl = fl;
    }
    public String getFl(){
        return this.fl;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
