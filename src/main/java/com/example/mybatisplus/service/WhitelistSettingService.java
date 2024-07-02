package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.TeacherDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-22
 */
public interface WhitelistSettingService extends IService<WhitelistSetting> {
// test001
    WhitelistSetting login(WhitelistSetting whitelistSetting);

    Page<WhitelistSetting> getUserList(PageDTO pageDTO);

    Page<WhitelistSetting> getTeacherList(TeacherDTO teacherDTO);

    List<WhitelistSetting> queryAll(WhitelistSetting whitelistSetting);

    boolean existUser(WhitelistSetting whitelistSetting);

    WhitelistSetting getBySn(String sn);
}
