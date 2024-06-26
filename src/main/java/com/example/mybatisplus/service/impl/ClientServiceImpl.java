package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Client;
import com.example.mybatisplus.mapper.ClientMapper;
import com.example.mybatisplus.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public boolean exist(Client client) {
        QueryWrapper<Client> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("phone_number", client.getPhoneNumber());

        QueryWrapper<Client> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("s_id", client.getSId());

        return !(clientMapper.selectList(queryWrapper1).isEmpty() && clientMapper.selectList(queryWrapper2).isEmpty());
    }

    @Override
    public boolean exist(Long id) {
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id);

        return clientMapper.selectById(id) != null;
    }
}
