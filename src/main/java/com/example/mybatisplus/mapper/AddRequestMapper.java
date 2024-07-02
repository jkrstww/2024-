package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.AddRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.dto.AddRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxp
 * @since 2024-07-02
 */
public interface AddRequestMapper extends BaseMapper<AddRequest> {

    void approve(@Param("dto") AddRequestDTO addRequestDTO);

    void reject(@Param("dto") AddRequestDTO addRequestDTO);

    void addRequest(@Param("dto") AddRequestDTO addRequestDTO);

    List<AddRequestDTO> getList(@Param("dto") AddRequestDTO addRequestDTO);
}
