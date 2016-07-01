package org.demo.web.api;


import org.demo.conf.sys.SysConf;
import org.demo.dto.CrdResult;
import org.demo.converter.ByteArrayToPhoneNumberConvert;
import org.demo.dto.EpsExposer;
import org.demo.editor.PhoneNumberEditor;
import org.demo.enums.CrdFrontStatEnum;
import org.demo.model.PhoneNumberModel;
import org.demo.model.UserModel;
import org.demo.service.EpsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hanhu on 16/6/20.
 */


@Controller
@RequestMapping("/api")//url:/模块/资源/{id}/细分
public class CrdFrontController {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysConf sysConf;

    @Autowired
    private EpsService epsService;


    //ajax json
    @RequestMapping(value = "/eps/opt/edit", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CrdResult<EpsExposer> optEdit(@RequestBody EpsExposer item)
    {
        CrdResult<EpsExposer> result;
        try {
            int num = epsService.updateEpsOpt(item);
            if(num == 0)
                result = new CrdResult<EpsExposer>(true, item);
            else
            {
                result = new CrdResult<EpsExposer>(false, CrdFrontStatEnum.UPDATE_FAIL.getStateInfo());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new CrdResult<EpsExposer>(false, e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    @ResponseBody
    public CrdResult<SysConf> location()
    {
        return new CrdResult(true,sysConf);
    }


    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public CrdResult<Long> time() {
        logger.debug("##########################");
        Date now = new Date();
        return new CrdResult(true, now.getTime());
    }




    //http://localhost:8080/crdfront/api/petest?date=2011-01-01&phone=010-12345678
    @RequestMapping(value = "/petest", method = RequestMethod.GET)
    @ResponseBody
    public String petest(@RequestParam("date") Date date , @RequestParam("phone") PhoneNumberModel phone)
    {
        return date.toString() + "," + phone.getAreaCode();
    }

    @InitBinder("date")
    public void initDate(WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));

    }


    @InitBinder("phone")
    public void initPhone(WebDataBinder binder)
    {
        binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());
    }

//
//    @RequestMapping(value = "/getPhone", method = RequestMethod.GET)
//    @ResponseBody
//    public CrdResult<PhoneNumberModel> getPhone(@RequestParam("phoneNumber")
//                                                  PhoneNumberModel phoneNumberModel)
//            throws Exception {
//        logger.info(phoneNumberModel.toString());
//        return new CrdResult(true,phoneNumberModel);
//    }

    //"consumes" : ["multipart/mixed; boundary=Boundary-----1234567890"]
    @RequestMapping(value = "/getBytesReq", method = RequestMethod.POST
          ,consumes={"application/octet-stream"}
    )
    @ResponseBody
    public CrdResult<UserModel> queryBytesReq(@RequestBody byte[] body)
            throws Exception {
       // logger.info(user.toString());
       // String resp  = new String(Hex.decode(data));
        //return new CrdResult(true,resp);

        ByteArrayToPhoneNumberConvert byteConvert = new ByteArrayToPhoneNumberConvert();
        UserModel user  = byteConvert.convert(body);

        return new CrdResult(true,user);
    }

    //http://localhost:8080/crdfront/api/getPhoneNumber/010-12345678
    @RequestMapping(value = "/getPhoneNumber/{phoneNumber}", method = RequestMethod.GET)
    @ResponseBody
    public CrdResult<PhoneNumberModel> queryPhone(@PathVariable(value = "phoneNumber")
                                                      PhoneNumberModel phoneNumberModel)
            throws Exception {
        logger.info(phoneNumberModel.toString());
        return new CrdResult(true,phoneNumberModel);
    }

    //http://localhost:8080/crdfront/api/getConf/dev,China
    @RequestMapping(value = "/getConf/{conf}", method = RequestMethod.GET)
    @ResponseBody
    public CrdResult<SysConf> queryConf(@PathVariable(value = "conf") SysConf sysConf) throws Exception {
        logger.info(sysConf.toString());
        return new CrdResult(true,sysConf);
    }
}
