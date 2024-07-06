package com.example.mybatisplus.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FirstVisitNotificationDTO {
    private String visitTeacher;
    private String visitDate;
    private String visitPeriod;
    private String visitLocation;
    private String sId;
}
