package org.demo.service;

import org.demo.dto.EpsExposer;

import java.util.List;

/**
 * Created by hanhu on 16/6/15.
 */
public interface EpsService {

    /**
     * 查询所有OPT记录
     *
     * @return
     */
    List<EpsExposer> getList();

    int updateEpsOpt(EpsExposer item);
}
