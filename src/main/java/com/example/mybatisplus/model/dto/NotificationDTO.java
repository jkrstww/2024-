package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NotificationDTO {
    private String consultTeacher;
    private String consultDate;
    private String consultPeriod;
    private String consultLocation;
    private String sId;
}
