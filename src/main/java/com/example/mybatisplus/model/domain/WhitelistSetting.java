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
 * @since 2024-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WhitelistSetting对象", description="")
public class WhitelistSetting extends Model<WhitelistSetting> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String sn;

    private String password;

    private String name;

    private Long roleId;

    @TableField("is_enabled")
    private Integer enabled;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    private String phoneNumber;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
