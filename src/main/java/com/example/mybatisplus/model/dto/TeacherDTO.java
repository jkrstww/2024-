package com.example.mybatisplus.model.dto;

import com.example.mybatisplus.model.domain.WhitelistSetting;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TeacherDTO {
    private PageDTO pageDTO;
    private WhitelistSetting whitelistSetting;
}
