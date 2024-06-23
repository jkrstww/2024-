package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Registration;
import com.example.mybatisplus.mapper.RegistrationMapper;
import com.example.mybatisplus.service.RegistrationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-23
 */
@Service
public class RegistrationServiceImpl extends ServiceImpl<RegistrationMapper, Registration> implements RegistrationService {

}
