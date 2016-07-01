package org.demo.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.demo.entity.eps.OptInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * 配置Spring和Junit整合,junit启动时加载springIOC容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class OptInfoDaoTest {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入Dao实现类依赖
    @Resource
    private OptInfoDao optInfoDao;
    @Test
    public void testQueryAll() throws Exception {
        List<OptInfo> opts = optInfoDao.queryAll();
        for (OptInfo opt : opts) {
            if("200000038816".equals(opt.getTid())) {
                assertNotNull(opt.getTid());
                assertEquals("200000038816", opt.getTid());
                logger.info(opt.toString());
            }
        }
    }

    @Test
    public void testInsertOpt() throws Exception {
        Date time = new Date();
        int insertCount = optInfoDao.insertOpt("200000038817","560000000001","b2e2cad4d6d0d0c432d5be","127.0.0.1",20002,time);
        assertEquals(1, insertCount);
        System.out.println("updateCount:  " + insertCount);
    }

    @Test
    public void testUpdateOpt() throws Exception {
        int updateCount = optInfoDao.updateOpt("200000038817","520000000001","127.0.0.1",20002);
        assertEquals(1, updateCount);
        System.out.println("updateCount:  " + updateCount);
    }
}

