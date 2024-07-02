package com.example.mybatisplus.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class VisitConclusionDTO {
    private Long id;
    private String sId;
    private String name;
    private Integer gender;
    private String campus;
    private String college;
    private String phoneNumber;
    @JsonFormat
    private LocalDateTime visitTime;
    private Integer riskRank;
    private String problem;
    private String conclusion;
    private String status;
}
