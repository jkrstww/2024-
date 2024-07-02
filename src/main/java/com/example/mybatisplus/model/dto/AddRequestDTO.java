package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddRequestDTO {
    private Long id;
    private String sn;
    private String name;
    private String consultant;
    private Integer times;
    private String status;
}
