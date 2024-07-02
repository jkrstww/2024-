package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.WhitelistSettingMapper;
import com.example.mybatisplus.model.domain.VisitRecord;
import com.example.mybatisplus.mapper.VisitRecordMapper;
import com.example.mybatisplus.model.domain.VisitRequest;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.VisitConclusionDTO;
import com.example.mybatisplus.model.dto.VisitConclusionSearchDTO;
import com.example.mybatisplus.service.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
@Service
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordMapper, VisitRecord> implements VisitRecordService {
    @Autowired
    private VisitRecordMapper visitRecordMapper;
    @Autowired
    private WhitelistSettingMapper whitelistSettingMapper;

    @Override
    public boolean teacherConflict(VisitRecord visitRecord) {
        if (visitRecord.getVisitTeacher() == null || visitRecord.getVisitTeacher().isEmpty()) {
            return false;
        }
        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visit_teacher", visitRecord.getVisitTeacher()).eq("visit_time", visitRecord.getVisitTime());
        return !visitRecordMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    public boolean locationConflict(VisitRecord visitRecord) {
        if (visitRecord.getVisitLocation() == null || visitRecord.getVisitLocation().isEmpty()) {
            return false;
        }
        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visit_location", visitRecord.getVisitLocation()).eq("visit_time", visitRecord.getVisitTime());
        return !visitRecordMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    public Page<VisitRecord> pageList(PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);
        return visitRecordMapper.selectPage(page, null);
    }

    @Override
    public boolean cancleAbled(Long id) {
        VisitRecord visitRecord = visitRecordMapper.selectById(id);
        // 待批准和已拒绝可以直接删
        if ("待批准".equals(visitRecord.getStatus()) || "已拒绝".equals(visitRecord.getStatus())) return true;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTime = visitRecord.getVisitTime();
        LocalDateTime lastCancleTime = visitTime.minusDays(1);

        return now.isBefore(lastCancleTime);
    }

    @Override
    public Page<VisitRecord> myPageList(String id, PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);

        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id);
        return visitRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<VisitRecord> myApprovedPageList(String id, PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);

        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id)
                .eq("is_approved", true);
        return visitRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<VisitRecord> unfinishedPageListByRisk(PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);

        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status", "已完成")
                .ne("status", "已拒绝")
                .orderByDesc("is_danger");
        return visitRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<VisitRecord> unfinishedPageListByTime(PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);

        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status", "已完成")
                .ne("status", "已拒绝")
                .orderByDesc("created_time");
        return visitRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<VisitRecord> awaitUpdatePageList(PageDTO pageDTO) {
        Integer PageNo = pageDTO.getPageNo();
        Integer PageSize = pageDTO.getPageSize();
        Page<VisitRecord> page = new Page<>(PageNo, PageSize);

        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "已批准").isNotNull("visit_teacher");
        return visitRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<VisitConclusionDTO> visitorQueryRecords(VisitConclusionSearchDTO visitConclusionSearchDTO) {
        Long id = SecurityUtils.getUserInfo().getId();
        WhitelistSetting whitelistSetting = whitelistSettingMapper.selectById(id);
        String name = whitelistSetting.getName();
        visitConclusionSearchDTO.setVisitor(name);

        Integer pageNo = visitConclusionSearchDTO.getPageNo();
        Integer pageSize = visitConclusionSearchDTO.getPageSize();
        return visitRecordMapper.visitorQueryRecords(visitConclusionSearchDTO, (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public Integer visitorQueryRecordsTotal(VisitConclusionSearchDTO visitConclusionSearchDTO) {
        Long id = SecurityUtils.getUserInfo().getId();
        WhitelistSetting whitelistSetting = whitelistSettingMapper.selectById(id);
        String name = whitelistSetting.getName();
        visitConclusionSearchDTO.setVisitor(name);

        return visitRecordMapper.visitQueryRecordsTotal(visitConclusionSearchDTO);
    }
}
