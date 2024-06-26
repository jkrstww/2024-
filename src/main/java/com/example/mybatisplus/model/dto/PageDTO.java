package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageDTO {
    private Integer pageNo;
    private Integer pageSize;
}
