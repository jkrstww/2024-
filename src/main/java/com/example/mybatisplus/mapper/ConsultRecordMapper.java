package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.dto.ConsultRecordDTO;
import com.example.mybatisplus.model.dto.ConsultRecordSearchDTO;
import com.example.mybatisplus.model.dto.ConsultTeacherDTO;
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
public interface ConsultRecordMapper extends BaseMapper<ConsultRecord> {

    List<ConsultRecord> match(@Param("consultRecord") ConsultRecord consultRecord);

    List<ConsultTeacherDTO> getConsultTimeEcharts();

    Integer consultantCheckRecordTotal(@Param("dto") ConsultRecordSearchDTO consultRecordSearchDTO);

    List<ConsultRecordDTO> consultantCheckRecord(@Param("dto") ConsultRecordSearchDTO consultRecordSearchDTO,
                                                 @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    Integer consultantQueryRecordTotal(@Param("dto") ConsultRecordSearchDTO consultRecordSearchDTO);

    List<ConsultRecordDTO> consultantQueryRecord(@Param("dto") ConsultRecordSearchDTO consultRecordSearchDTO,
                                                 @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);
}
