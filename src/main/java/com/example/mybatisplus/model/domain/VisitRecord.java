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
 * @since 2024-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VisitRecord对象", description="")
public class VisitRecord extends Model<VisitRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    @TableField("s_id")
    private String sId;

    @TableField("visit_teacher")
    private String visitTeacher;

    @TableField("visit_location")
    private String visitLocation;

    @TableField("visit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime visitTime;

    @TableField("risk_rank")
    private Integer riskRank;

    private String problem;

    @JsonProperty("pId")
    private Integer pId;

    private String conclusion;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
