package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VisitConclusionSearchDTO {
    private String sId;
    private String Name;
    private String campus;
    private String college;
    private String visitor;

    private Integer pageNo=1;
    private Integer pageSize=10;
}
