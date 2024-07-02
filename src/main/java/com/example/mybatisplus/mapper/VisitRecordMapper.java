package com.example.mybatisplus.mapper;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.VisitRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.dto.ConsultTeacherDTO;
import com.example.mybatisplus.model.dto.VisitConclusionDTO;
import com.example.mybatisplus.model.dto.VisitConclusionSearchDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface VisitRecordMapper extends BaseMapper<VisitRecord> {
    List<ConsultTeacherDTO> getDTO();

    Integer visitQueryRecordsTotal(@Param("r") VisitConclusionSearchDTO visitConclusionSearchDTO);

    List<VisitConclusionDTO> visitorQueryRecords(@Param("r") VisitConclusionSearchDTO visitConclusionSearchDTO,
                                                 @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}
