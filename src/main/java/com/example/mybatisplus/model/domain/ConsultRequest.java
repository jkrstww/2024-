package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value="ConsultRequest对象", description="")
public class ConsultRequest extends Model<ConsultRequest> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createdTime;

    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;

    private String sId;

    private Integer isDanger;

    private Integer isHandled;

    private Integer isApproved;

    private Integer isFinished;

    private LocalDateTime finishTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
