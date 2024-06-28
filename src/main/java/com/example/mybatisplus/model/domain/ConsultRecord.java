package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
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
@ApiModel(value="ConsultRecord对象", description="")
public class ConsultRecord extends Model<ConsultRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;

    @TableField("s_id")
    private String sId;

    @TableField("report_id")
    private Long reportId;

    @TableField("consult_teacher")
    private String consultTeacher;

//    @TableField("consult_location")
    private String consultLocation;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consultTime;


    private String consultStatus;

    private Long pId;

    private byte[] phoneNumber;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
