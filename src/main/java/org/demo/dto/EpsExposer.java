package org.demo.dto;

/**
 * Created by hanhu on 16/6/20.
 */


import cfca.org.bouncycastle.util.encoders.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.entity.eps.OptInfo;
import org.demo.util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO(dto:web层和service层传递数据用)
 */
public class EpsExposer
{
    private static Log logger = LogFactory.getLog(EpsExposer.class);

    private String tid;


    private String psamTid;

    private String stationName;

    private Date createTime;

    private String ip;

    @Override
    public String toString() {
        try {
            return JsonUtil.bean2Json(this);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int port;


    public EpsExposer() {

    }


    public EpsExposer(OptInfo info) throws Exception
    {
        if(null != info)
        {
            this.createTime = info.getCreateTime();
            this.tid = info.getTid();
            this.ip = info.getIp();
            this.port = info.getPort();
            this.psamTid = info.getPsamTid();
            if(null != info.getStationName())
            {
                byte[]  temp = Hex.decode(info.getStationName());
                this.stationName = new String(temp,"GBK");
            }
        }
    }

    public static List<EpsExposer> getList(List<OptInfo> list)
    {
        if(list != null)
        {
            try{
                List tlist  = new ArrayList<EpsExposer>();
                for(int i = 0; i < list.size(); i++)
                {
                    OptInfo item = list.get(i);
                    tlist.add(new EpsExposer(item));

                }

                return tlist;
            }
            catch (Exception ex)
            {
                logger.error(ex.getMessage());
            }

        }
        return null;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPsamTid() {
        return psamTid;
    }

    public void setPsamTid(String psamTid) {
        this.psamTid = psamTid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
