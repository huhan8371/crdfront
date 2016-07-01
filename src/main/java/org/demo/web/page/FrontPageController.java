package org.demo.web.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.dto.EpsExposer;
import org.demo.service.EpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hanhu on 16/6/18.
 */


@Controller
@RequestMapping("/admin")//url:/模块/资源/{id}/细分
public class FrontPageController
{
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private EpsService epsService;


    @RequestMapping(value = "/eps/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<EpsExposer> list = epsService.getList();
        model.addAttribute("list", list);
        return "/eps/list";
    }
}
