package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Client;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface ClientService extends IService<Client> {
    boolean exist(Client client);
    boolean exist(Long id);
}
