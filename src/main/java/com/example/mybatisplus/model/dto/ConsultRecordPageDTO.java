package com.example.mybatisplus.model.dto;

import com.example.mybatisplus.model.domain.ConsultRecord;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsultRecordPageDTO {
    private ConsultRecord consultRecord;
    private PageDTO pageDTO;
}
