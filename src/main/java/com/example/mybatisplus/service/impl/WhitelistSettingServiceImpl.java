package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.mapper.WhitelistSettingMapper;
import com.example.mybatisplus.service.WhitelistSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-22
 */
@Service
public class WhitelistSettingServiceImpl extends ServiceImpl<WhitelistSettingMapper, WhitelistSetting> implements WhitelistSettingService {
    @Autowired
    private WhitelistSettingMapper whitelistSettingMapper;
    @Override
    public WhitelistSetting login(WhitelistSetting whitelistSetting) {
        LambdaQueryWrapper<WhitelistSetting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WhitelistSetting::getSn, whitelistSetting.getSn())
                .eq(WhitelistSetting::getPassword, whitelistSetting.getPassword())
                .eq(WhitelistSetting::getEnabled, true);
        return whitelistSettingMapper.selectOne(wrapper);
    }
}
