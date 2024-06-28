package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.OnJob;
import com.example.mybatisplus.mapper.OnJobMapper;
import com.example.mybatisplus.service.OnJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-23
 */
@Service
public class OnJobServiceImpl extends ServiceImpl<OnJobMapper, OnJob> implements OnJobService {
    @Autowired
    private OnJobMapper onJobMapper;

    @Override
    public boolean locationConflicted(OnJob onJob) {
        QueryWrapper<OnJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_location", onJob.getJobLocation()).eq("job_time", onJob.getJobTime());
        return !onJobMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    public boolean personConflicted(OnJob onJob) {
        QueryWrapper<OnJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id", onJob.getPId()).eq("job_time", onJob.getJobTime());
        return !onJobMapper.selectList(queryWrapper).isEmpty();
    }
}
