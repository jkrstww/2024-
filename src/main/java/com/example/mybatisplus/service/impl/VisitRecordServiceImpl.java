package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.VisitRecord;
import com.example.mybatisplus.mapper.VisitRecordMapper;
import com.example.mybatisplus.model.domain.VisitRequest;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public boolean teacherConflict(VisitRecord visitRecord) {
        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visit_teacher", visitRecord.getVisitTeacher()).eq("visit_time", visitRecord.getVisitTime());
        return !visitRecordMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    public boolean locationConflict(VisitRecord visitRecord) {
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
    public boolean cancleAbled(Integer id) {
        VisitRecord visitRecord = visitRecordMapper.selectById(id);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime visitTime = visitRecord.getVisitTime();
        LocalDateTime lastCancleTime = visitTime.minusDays(1);

        return now.isBefore(lastCancleTime);
    }
}
