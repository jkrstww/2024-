package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxp
 * @since 2024-06-26
 */
public interface ReportMapper extends BaseMapper<Report> {

    Integer recordTimes(@Param("sn") String sn, @Param("consultant") String consultant);

    void finishRecord(@Param("sn") String sn, @Param("consultant") String consultant);
}
