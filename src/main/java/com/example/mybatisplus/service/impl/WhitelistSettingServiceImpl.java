package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.mapper.WhitelistSettingMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.TeacherDTO;
import com.example.mybatisplus.service.WhitelistSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public Page<WhitelistSetting> getUserList(PageDTO pageDTO) {
        Page<WhitelistSetting> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());

        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();

        return whitelistSettingMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<WhitelistSetting> getTeacherList(TeacherDTO teacherDTO) {
        Page<WhitelistSetting> page = new Page<>();
        page.setCurrent(teacherDTO.getPageDTO().getPageNo()).setSize(teacherDTO.getPageDTO().getPageSize());

        WhitelistSetting whitelistSetting = new WhitelistSetting()
                .setSn(teacherDTO.getWhitelistSetting().getSn())
                .setName(teacherDTO.getWhitelistSetting().getName());

        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", Arrays.asList(3, 4));
        if (whitelistSetting.getSn() != null && !whitelistSetting.getSn().isEmpty()) {
            queryWrapper.eq("sn", whitelistSetting.getSn());
        }
        if (whitelistSetting.getName() != null && !whitelistSetting.getName().isEmpty()) {
            queryWrapper.like("name", whitelistSetting.getName());
        }

        return whitelistSettingMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<WhitelistSetting> queryAll(WhitelistSetting whitelistSetting) {
        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();
        if (whitelistSetting.getSn() != null && !whitelistSetting.getSn().isEmpty()) {
            queryWrapper.eq("sn", whitelistSetting.getSn());
        }
        if (whitelistSetting.getId() != null) {
            queryWrapper.eq("id", whitelistSetting.getId());
        }
        if (whitelistSetting.getRoleId() != null) {
            queryWrapper.eq("role_id", whitelistSetting.getRoleId());
        }
        if (whitelistSetting.getPassword() != null && !whitelistSetting.getPassword().isEmpty()) {
            queryWrapper.eq("password", whitelistSetting.getPassword());
        }
        if (whitelistSetting.getName() != null && !whitelistSetting.getName().isEmpty()) {
            queryWrapper.eq("name", whitelistSetting.getName());
        }
        if (whitelistSetting.getPhoneNumber() != null && !whitelistSetting.getPhoneNumber().isEmpty()) {
            queryWrapper.eq("phoneNumber", whitelistSetting.getPhoneNumber());
        }

        return whitelistSettingMapper.selectList(queryWrapper);
    }

    @Override
    public boolean existUser(WhitelistSetting whitelistSetting) {
        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", whitelistSetting.getSn());

        List<WhitelistSetting> list = whitelistSettingMapper.selectList(queryWrapper);
        return !list.isEmpty();
    }

    @Override
    public WhitelistSetting getBySn(String sn) {
        QueryWrapper<WhitelistSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", sn);

        return whitelistSettingMapper.selectOne(queryWrapper);
    }

}
