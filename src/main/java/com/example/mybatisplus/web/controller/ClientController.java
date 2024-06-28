package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ClientService;
import com.example.mybatisplus.model.domain.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/insert")
    public JsonResponse insert(@RequestBody Client client) {
        // 是否存在该用户 电话号码或学号唯一
        if (clientService.exist(client)) {
            return JsonResponse.failure("该用户已存在");
        } else {
            clientService.save(client);
            return JsonResponse.success("添加成功");
        }
    }

    @GetMapping("/deleteById")
    public JsonResponse deleteById(@RequestParam Long id) {
        // 是否存在该用户 电话号码或学号唯一
        if (clientService.exist(id)) {
            clientService.removeById(id);
            return JsonResponse.success("删除成功");
        } else {
            return JsonResponse.failure("该用户已存在");
        }
    }

    @PostMapping("/update")
    public JsonResponse update(@RequestBody Client client) {
        clientService.updateById(client);
        return JsonResponse.success("修改成功");
    }

    @RequestMapping("/getVisitPeople")
    public JsonResponse getVisitPeople() {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", 2);
        return JsonResponse.success(clientService.list(queryWrapper));
    }

    @RequestMapping("/getConsultPeople")
    public JsonResponse getConsultPeople() {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", 4);
        return JsonResponse.success(clientService.list(queryWrapper));
    }

    @PostMapping("/login")
    public JsonResponse login(HttpServletRequest request, @RequestBody Client client) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", client.getSId())
                .eq("password", client.getPassword());
        List<Client> list = clientService.list(queryWrapper);
        if (!list.isEmpty()) {
            //第一步：获取session
            HttpSession session = request.getSession();
            //第二步：将想要保存到数据存入session中
            session.setAttribute("sn",client.getSId());
            return JsonResponse.success(1);
        } else return JsonResponse.failure("错误");
    }
}

