package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.VisitRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-23
 */
public interface VisitRequestService extends IService<VisitRequest> {

    Page<VisitRequest> pageSortList(PageDTO pageDTO);

    Page<VisitRequest> pageList(PageDTO pageDTO);

    Page<VisitRequest> riskPageList(PageDTO pageDTO);
}
