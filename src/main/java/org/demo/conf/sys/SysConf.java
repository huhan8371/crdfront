package org.demo.conf.sys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hanhu on 16/6/23.
 */

@Component
public class SysConf
{

    @Value("#{sysProperties['key']}")
    private String key;
    private String location;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
