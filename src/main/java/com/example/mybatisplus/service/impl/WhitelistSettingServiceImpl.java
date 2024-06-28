package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.utls.SessionUtils;
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
        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", whitelistSetting.getSn())
                .eq("password", whitelistSetting.getPassword())
                .eq("is_enabled", true);

        WhitelistSetting one = whitelistSettingMapper.selectOne(queryWrapper);
        SessionUtils.saveCurrentUserInfo(one);

        return whitelistSettingMapper.selectOne(queryWrapper);
    }
}
