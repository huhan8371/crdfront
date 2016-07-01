package org.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.demo.dto.EpsExposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class EpsServiceTest {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EpsService epsService;


    @Test
    public void testGetList() throws Exception {

       List<EpsExposer> list =  epsService.getList();
        assertNotNull(list);
        logger.info("##########" + list.size());
        EpsExposer exposer = list.get(1);
        assertNotNull(exposer);
        assertEquals("200000038816", exposer.getTid());

        logger.info("##########" + exposer.getStationName());
    }
}