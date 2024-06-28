package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.ConsultRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface ConsultRequestMapper extends BaseMapper<ConsultRequest> {


    void insertnewRequest(@Param("consultRequest") ConsultRequest consultRequest);

    void judgeconflict(@Param("consultRequest") ConsultRequest consultRequest);
}
