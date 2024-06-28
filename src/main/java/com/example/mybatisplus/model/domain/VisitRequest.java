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
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lxp
 * @since 2024-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VisitRequest对象", description="")
@ToString
public class VisitRequest extends Model<VisitRequest> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    @TableField("s_id")
    @JsonProperty(value = "sId")
    private String sId;

    // @TableField("is_danger")
    private Integer isDanger;

    @TableField("is_handled")
    private Integer isHandled;

    @TableField("is_approved")
    private Integer isApproved;

    @TableField("is_finished")
    private Integer isFinished;

    @TableField("finish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
