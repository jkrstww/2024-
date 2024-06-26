package com.example.mybatisplus.model.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsultTeacherDTO {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("初访员")
    private String name;
    @ExcelProperty("次数")
    private Integer times;
    @ExcelProperty("总时间")
    private Integer sumTime;
}
