package com.example.mybatisplus.model.dto;

import com.example.mybatisplus.model.domain.Report;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReportPageDTO {
    private Report report;
    private PageDTO pageDTO;
}
