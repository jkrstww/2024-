package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-30
 */
public interface StudentService extends IService<Student> {

    Student getBySn(String sn);
}
