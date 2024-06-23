package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-22
 */
public interface WhitelistSettingService extends IService<WhitelistSetting> {

    WhitelistSetting login(WhitelistSetting whitelistSetting);
}
