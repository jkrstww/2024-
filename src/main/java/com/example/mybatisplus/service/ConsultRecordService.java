package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface ConsultRecordService extends IService<ConsultRecord> {

    void changetime(ConsultRecord consultRecord);

    void changelocation(ConsultRecord consultRecord);

    void changeteacher(ConsultRecord consultRecord);

    int matchTacherandStudent(ConsultRecord consultRecord);
}
