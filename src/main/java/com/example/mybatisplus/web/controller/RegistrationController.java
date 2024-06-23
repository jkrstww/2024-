package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.RegistrationService;
import com.example.mybatisplus.model.domain.Registration;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-23
 * @version v1.0
 */
@Controller
@RequestMapping("/api/registration")
public class RegistrationController {

    private final Logger logger = LoggerFactory.getLogger( RegistrationController.class );

    @Autowired
    private RegistrationService registrationService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Registration  registration =  registrationService.getById(id);
        return JsonResponse.success(registration);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        registrationService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateRegistration(Registration  registration) throws Exception {
        registrationService.updateById(registration);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Registration
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Registration  registration) throws Exception {
        registrationService.save(registration);
        return JsonResponse.success(null);
    }
}

