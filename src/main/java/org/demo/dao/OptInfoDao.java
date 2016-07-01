package org.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.demo.entity.eps.OptInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by hanhu on 16/6/15.
 */
public interface OptInfoDao {

    /**
     * 查询Opt列表
     *
     * @return
     */
    List<OptInfo> queryAll();

    int updateOpt(@Param("tid") String tid,@Param("psamTid") String psamTid,
                  @Param("ip") String ip,
                  @Param("port") int port);

    int insertOpt(@Param("tid") String tid,@Param("psamTid") String psamTid,
                  @Param("stationName") String stationName,@Param("ip") String ip,
                  @Param("port") int port,@Param("createTime") Date now);


}
