package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.AddRequest;
import com.example.mybatisplus.mapper.AddRequestMapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.dto.AddRequestDTO;
import com.example.mybatisplus.service.AddRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-07-02
 */
@Service
public class AddRequestServiceImpl extends ServiceImpl<AddRequestMapper, AddRequest> implements AddRequestService {
    @Autowired
    private AddRequestMapper addRequestMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean isRequested(AddRequestDTO addRequestDTO) {
        QueryWrapper<AddRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", addRequestDTO.getSn())
            .eq("name", addRequestDTO.getName())
            .eq("consultant", addRequestDTO.getConsultant())
            .eq("status", "已批准");

        List<AddRequest> list = addRequestMapper.selectList(queryWrapper);
        return !list.isEmpty();
    }

    @Override
    public boolean exitRecord(AddRequestDTO addRequestDTO) {
        QueryWrapper<AddRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", addRequestDTO.getSn())
                .eq("name", addRequestDTO.getName())
                .eq("consultant", addRequestDTO.getConsultant());

        List<AddRequest> list = addRequestMapper.selectList(queryWrapper);
        return !list.isEmpty();
    }

    @Override
    public void addRequest(AddRequestDTO addRequestDTO) {
        // 存在记录，只需要修改为待批准
        if (exitRecord(addRequestDTO)) {
            addRequestMapper.addRequest(addRequestDTO);
        } else {
            AddRequest addRequest = new AddRequest()
                    .setSn(addRequestDTO.getSn())
                    .setName(addRequestDTO.getName())
                    .setConsultant(addRequestDTO.getConsultant());
            addRequestMapper.insert(addRequest);
        }
    }

    @Override
    public void approve(AddRequestDTO addRequestDTO) {
        addRequestMapper.approve(addRequestDTO);
    }

    @Override
    public void reject(AddRequestDTO addRequestDTO) {
        addRequestMapper.reject(addRequestDTO);
    }

    @Override
    public List<AddRequestDTO> getList(AddRequestDTO addRequestDTO) {
        List<AddRequestDTO> listWithoutStatus = addRequestMapper.getList(addRequestDTO);

        for (AddRequestDTO dto : listWithoutStatus) {
            // 获得学生名字
            QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("sn", dto.getSn());
            Student student = studentMapper.selectOne(queryWrapper1);
            dto.setName(student.getName());

            QueryWrapper<AddRequest> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sn", dto.getSn())
                    .eq("name", dto.getName())
                    .eq("consultant", dto.getConsultant());
            List<AddRequest> a = addRequestMapper.selectList(queryWrapper);

            String status;
            if (a.isEmpty()) {
                status = "暂无";
            } else {
                status = a.get(0).getStatus();
            }

            dto.setStatus(status);
        }

        return listWithoutStatus;
    }
}
