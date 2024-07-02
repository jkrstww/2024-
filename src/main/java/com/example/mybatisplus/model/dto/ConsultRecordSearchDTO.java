package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsultRecordSearchDTO {
    private String name;
    private String consultant;
    private String sn;
    private Integer pageNo;
    private Integer pageSize;
}
