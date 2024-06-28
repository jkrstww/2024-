package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
