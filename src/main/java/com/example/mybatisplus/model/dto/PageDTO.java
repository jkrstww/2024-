package com.example.mybatisplus.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageDTO {
    @JsonProperty("pageNo")
    private Integer pageNo;
    @JsonProperty("pageSize")
    private Integer pageSize;
}
