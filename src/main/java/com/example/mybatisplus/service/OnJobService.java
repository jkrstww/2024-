package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.OnJob;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-23
 */
public interface OnJobService extends IService<OnJob> {
    // 地点冲突
    boolean locationConflicted(OnJob onJob);

    // 人员冲突
    boolean personConflicted(OnJob onJob);
}
