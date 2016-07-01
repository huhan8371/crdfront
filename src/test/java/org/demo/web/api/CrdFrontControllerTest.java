package org.demo.web.api;

import cfca.org.bouncycastle.util.encoders.Hex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.demo.dto.EpsExposer;
import org.demo.model.PhoneNumberModel;
import org.demo.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * Created by hanhu on 16/6/20.
 */



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/*.xml"})
public class CrdFrontControllerTest {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EpsExposer item;

    @InjectMocks
    private CrdFrontController controller;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    @Before
    public void setUp() throws Exception
    {
        item = new EpsExposer();
        item.setIp("127.0.0.1");
        item.setPort(20002);
        item.setPsamTid("520000038816");
        item.setTid("200000038816");
        item.setCreateTime(new Date());
        item.setStationName("测试站点A");

        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testOptEdit() throws Exception{
        String req  = item.toString();
        logger.debug("req: " + req);

        ResultActions resultActions =  mvc.perform(
                post("/api/eps/opt/edit")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req));

        MvcResult mvcResult = resultActions.andReturn();

        String resp = mvcResult.getResponse().getContentAsString();

        Map map = JsonUtil.json2Bean(resp,Map.class);

        logger.debug("resp: " + resp);

        assertNotNull(map);
        boolean flag = (Boolean) map.get("success");
        assertTrue(flag);
    }

    @Test
    public void testLocation() throws Exception {
        ResultActions resultActions =  mvc.perform(
                get("/api/location"));

        MvcResult mvcResult = resultActions.andReturn();

        String resp = mvcResult.getResponse().getContentAsString();

        Map map = JsonUtil.json2Bean(resp,Map.class);

        logger.debug("resp: " + resp);

        assertNotNull(map);
        boolean flag = (Boolean) map.get("success");
        assertTrue(flag);
    }

    @Test
    public void testTime() throws Exception {

    }

//    @Test
//    public void testConverter() throws Exception {
//        String req  = item.toString();
//        byte[] data  = Hex.encode(req.getBytes());
//        logger.debug("req: " + new String(Hex.encode(data)));
//
//        ResultActions resultActions =  mvc.perform(
//                post("/api/converter")
//                        .content(data));
//
//        MvcResult mvcResult = resultActions.andReturn();
//
//        String resp = mvcResult.getResponse().getContentAsString();
//
//        logger.debug("resp: " + resp);
//
//    }

    @Test
    public void testQueryPhone() throws Exception {
        String phone  = "010-12345678";
        ResultActions resultActions =  mvc.perform(
                get("/api/getPhoneNumber/" + phone));

        MvcResult mvcResult = resultActions.andReturn();

        String resp = mvcResult.getResponse().getContentAsString();
        logger.debug("resp: " + resp);
        Map map = JsonUtil.json2Bean(resp,Map.class);



        assertNotNull(map);
        boolean flag = (Boolean) map.get("success");
        assertTrue(flag);


        ObjectMapper mapper = new ObjectMapper();
        String jsonfromMap =  mapper.writeValueAsString(map.get("data"));
        PhoneNumberModel model =  mapper.readValue(jsonfromMap, PhoneNumberModel.class);
        assertEquals("010",model.getAreaCode());
        assertEquals("12345678",model.getPhoneNumber());

    }

    @Test
    public void testQueryBytesReq() throws Exception {
        String user  = "010-12345678";
        byte[] reqBytes = Hex.encode(user.getBytes());
        ResultActions resultActions =  mvc.perform(
                post("/plugin/getBytesReq/")
                        .contentType("application/octet-stream")
                        .content(reqBytes)
        );
        MvcResult mvcResult = resultActions.andReturn();

        String resp = mvcResult.getResponse().getContentAsString();
        logger.info("resp: " + resp);

//        Map map = JsonUtil.json2Bean(resp,Map.class);
//
//
//        assertNotNull(map);
//        boolean flag = (Boolean) map.get("success");
//        assertTrue(flag);
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonfromMap =  mapper.writeValueAsString(map.get("data"));
//        PhoneNumberModel model =  mapper.readValue(jsonfromMap, PhoneNumberModel.class);
//        assertEquals("010",model.getAreaCode());
//        assertEquals("12345678",model.getPhoneNumber());
    }
}