package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.ConsultRequest;
import com.example.mybatisplus.mapper.ConsultRequestMapper;
import com.example.mybatisplus.service.ConsultRequestService;
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
public class ConsultRequestServiceImpl extends ServiceImpl<ConsultRequestMapper, ConsultRequest> implements ConsultRequestService {
    @Autowired
    private ConsultRequestMapper consultRequestMapper;
    @Override
    public List<ConsultRequest> getAllPendingRequests() {
            // 根据实际需求过滤未处理的申请
            QueryWrapper<ConsultRequest> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_handled", 0);
            return consultRequestMapper.selectList(queryWrapper);
    }

    @Override
    public void rejectRequest(String id) {
        ConsultRequest consultRequest = consultRequestMapper.selectById(id);
        if (consultRequest != null) {
            consultRequest.setIsHandled(1);
            consultRequest.setIsApproved(0);
            consultRequestMapper.updateById(consultRequest);
        }
    }

    @Override
    public void completeRequest(String id) {
        ConsultRequest consultRequest = consultRequestMapper.selectById(id);
        if (consultRequest != null) {
            consultRequest.setIsFinished(1);
            consultRequest.setFinishTime(LocalDateTime.now());
            consultRequestMapper.updateById(consultRequest);
        }
    }

    @Override
    public void acceptRequest(String id) {
        ConsultRequest consultRequest = consultRequestMapper.selectById(id);
        if (consultRequest != null) {
            consultRequest.setIsHandled(1);
            consultRequest.setIsApproved(1);
            consultRequestMapper.updateById(consultRequest);
        }
    }

    @Override
    public void insertnewRequest(ConsultRequest consultRequest) {
        consultRequestMapper.judgeconflict(consultRequest);
        consultRequestMapper.insertnewRequest(consultRequest);
    }
}
