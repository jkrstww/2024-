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
@ApiModel(value="Client对象", description="")
public class Client extends Model<Client> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer deleted;

    private String name;

    private String phoneNumber;

    // 学生 管理员 初访员 心理助教 咨询师
    private Integer roleId;

    @JsonProperty("sn")
    private String sId;

    private String password;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
