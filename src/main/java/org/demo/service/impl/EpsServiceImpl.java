package org.demo.service.impl;

import org.demo.dao.OptInfoDao;
import org.demo.dto.EpsExposer;
import org.demo.entity.eps.OptInfo;
import org.demo.exception.CrdException;
import org.demo.exception.UpdateOptException;
import org.demo.service.EpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EpsServiceImpl implements EpsService {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OptInfoDao optInfoDao;


    @Override
    public List<EpsExposer> getList() {
        List<OptInfo> tlist = new ArrayList<>();
        try
        {
            tlist = optInfoDao.queryAll();
            List<EpsExposer> list = EpsExposer.getList(tlist);
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
            //所有的编译期异常转化为运行期异常,spring的声明式事务做rollback
            throw new CrdException("inner error: " + e.getMessage());
        }
    }

    @Override
    public int updateEpsOpt(EpsExposer item)
    {
        int updateCount = 0;
            try
            {
                updateCount = optInfoDao.updateOpt(item.getTid(),item.getPsamTid(),
                    item.getIp(),item.getPort());

            } catch (UpdateOptException e1) {
                throw e1;
            } catch (Exception e) {
                logger.error(e.getMessage());
                //所有的编译期异常转化为运行期异常,spring的声明式事务做rollback
                throw new CrdException("inner error: " + e.getMessage());
            }
        return updateCount;
    }
}
