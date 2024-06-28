package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lxp
 * @since 2024-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Report对象", description="")
public class Report extends Model<Report> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;

    @JsonProperty("sId")
    private String sId;

    @JsonProperty("sName")
    private String sName;

    @JsonProperty("sGender")
    private Integer sGender;

    @JsonProperty("sAcademy")
    private String sAcademy;

    @JsonProperty("sPhone")
    private String sPhone;

    @JsonProperty("problem")
    private String problem;

    @JsonProperty("consultTeacher")
    private String consultTeacher;

    @JsonProperty("evaluation")
    private String evaluation;

    @JsonProperty("times")
    private Integer times;

    @JsonProperty("age")
    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
