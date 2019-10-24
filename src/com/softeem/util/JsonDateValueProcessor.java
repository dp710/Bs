package com.softeem.util;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
    private String format = "yyyy-MM-dd HH:mm:ss";
    
    public JsonDateValueProcessor() {
        super();
    }
    
    public JsonDateValueProcessor(String format) {
        super();
        this.format = format;
    }
    
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value instanceof Timestamp[]){//----->Date 类型 Timestamp 全改 Date
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Timestamp[] dates = (Timestamp[]) value;
            obj = new String[dates.length];
            for (int i=0; i<dates.length; i++){
                obj[i] = sf.format(dates[i]);
            }
        }
        return obj;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value instanceof Timestamp) {  
            String str = new SimpleDateFormat(format).format((Timestamp) value);  
            return str;  
        }  
        return value == null ? null : value.toString();  
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}