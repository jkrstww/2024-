package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface ConsultRecordService extends IService<ConsultRecord> {

    List<ConsultRecordDTO> consultantCheckRecord(ConsultRecordSearchDTO consultRecordSearchDTO) ;

    Integer consultantCheckRecordTotal(ConsultRecordSearchDTO consultRecordSearchDTO);

    void changetime(ConsultRecord consultRecord);

    void changelocation(ConsultRecord consultRecord);

    void changeteacher(ConsultRecord consultRecord);

    int matchTacherandStudent(ConsultRecord consultRecord);

    List<ConsultTeacherDTO> getConsultTimeEcharts();

    Page<ConsultRecord> getMyListPage(String sn, PageDTO pageDTO);

    Page<ConsultRecord> getRequest(PageDTO pageDTO);

    Page<ConsultRecord> queryPage(ConsultRecord consultRecord, PageDTO pageDTO);


    boolean locationConflict(ConsultRecord consultRecord);

    boolean teacherConflict(ConsultRecord consultRecord);

    Integer consultantQueryRecordTotal(ConsultRecordSearchDTO consultRecordSearchDTO);

    List<ConsultRecordDTO> consultantQueryRecord(ConsultRecordSearchDTO consultRecordSearchDTO);

    void sendNotification(NotificationDTO notificationDTO, ConsultRecord consultRecord);
}
