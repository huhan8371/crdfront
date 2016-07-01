package org.demo.entity.eps;

import java.util.Date;

/**
 * Created by hanhu on 16/6/15.
 */

public class OptInfo
{
    @Override
    public String toString() {
        return "OptInfo{" +
                "tid='" + tid + '\'' +
                ", iptId='" + iptId + '\'' +
                ", psamTid='" + psamTid + '\'' +
                ", psamNum=" + psamNum +
                ", stationName='" + stationName + '\'' +
                ", createTime=" + createTime +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    private String tid;

    private String iptId;

    private String psamTid;

    private int psamNum;

    private String stationName;

    private Date createTime;

    private String ip;

    private int port;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getIptId() {
        return iptId;
    }

    public void setIptId(String iptId) {
        this.iptId = iptId == null ? null : iptId.trim();
    }

    public String getPsamTid() {
        return psamTid;
    }

    public void setPsamTid(String psamTid) {
        this.psamTid = psamTid == null ? null : psamTid.trim();
    }

    public int getPsamNum() {
        return psamNum;
    }


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPsamNum(int psamNum) {
        this.psamNum = psamNum;
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