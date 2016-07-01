package org.demo.web.api;

import org.demo.converter.ByteArrayToPhoneNumberConvert;
import org.demo.dto.CrdResult;
import org.demo.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by hanhu on 16/6/20.
 */


@Controller
@RequestMapping("/plugin")//url:/模块/资源/{id}/细分
public class PluginController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());



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

}
