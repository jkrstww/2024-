package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.VisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.FirstVisitNotificationDTO;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.VisitConclusionDTO;
import com.example.mybatisplus.model.dto.VisitConclusionSearchDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface VisitRecordService extends IService<VisitRecord> {

    boolean teacherConflict(VisitRecord visitRecord);

    boolean locationConflict(VisitRecord visitRecord);

    Page<VisitRecord> pageList(PageDTO pageDTO);

    boolean cancleAbled(Long id);

    Page<VisitRecord> myPageList(String id, PageDTO pageDTO);

    Page<VisitRecord> myApprovedPageList(String id, PageDTO pageDTO);

    Page<VisitRecord> unfinishedPageListByRisk(PageDTO pageDTO);

    Page<VisitRecord> unfinishedPageListByTime(PageDTO pageDTO);

    Page<VisitRecord> awaitUpdatePageList(PageDTO pageDTO);

    List<VisitConclusionDTO> visitorQueryRecords(VisitConclusionSearchDTO visitConclusionSearchDTO);

    Integer visitorQueryRecordsTotal(VisitConclusionSearchDTO visitConclusionSearchDTO);

    void sendVisitNotification(FirstVisitNotificationDTO firstVisitNotificationDTO, VisitRecord visitRecord);
}
