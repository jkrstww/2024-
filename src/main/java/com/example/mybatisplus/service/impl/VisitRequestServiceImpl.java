package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.VisitRequest;
import com.example.mybatisplus.mapper.VisitRequestMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.VisitRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VisitRequestServiceImpl extends ServiceImpl<VisitRequestMapper, VisitRequest> implements VisitRequestService {
    @Autowired
    private VisitRequestMapper visitRequestMapper;

    @Override
    public Page<VisitRequest> pageSortList(PageDTO pageDTO) {
        Page<VisitRequest> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());
        QueryWrapper<VisitRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("is_danger");
        visitRequestMapper.selectPage(page, queryWrapper);

        return page;
    }

    @Override
    public Page<VisitRequest> pageList(PageDTO pageDTO) {
        Page<VisitRequest> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());
        LambdaQueryWrapper<VisitRequest> queryWrapper = new LambdaQueryWrapper<>();
        visitRequestMapper.selectPage(page, queryWrapper);

        return page;
    }

    @Override
    public Page<VisitRequest> riskPageList(PageDTO pageDTO) {
        Page<VisitRequest> page = new Page<>();
        page.setCurrent(pageDTO.getPageNo()).setSize(pageDTO.getPageSize());
        QueryWrapper<VisitRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_danger", 1);
        visitRequestMapper.selectPage(page, queryWrapper);

        return page;
    }
}
