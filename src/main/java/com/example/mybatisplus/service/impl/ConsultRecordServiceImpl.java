package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.ConsultRecord;
import com.example.mybatisplus.mapper.ConsultRecordMapper;
import com.example.mybatisplus.service.ConsultRecordService;
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
 * @since 2024-06-24
 */
@Service
public class ConsultRecordServiceImpl extends ServiceImpl<ConsultRecordMapper, ConsultRecord> implements ConsultRecordService {
    @Autowired
    private ConsultRecordMapper consultRecordMapper;

    @Override
    public void changetime(ConsultRecord consultRecord) {
        UpdateWrapper<ConsultRecord> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",consultRecord.getId()).set("consult_time",consultRecord.getConsultTime());
        consultRecordMapper.updateById(consultRecord);
    }
    @Override
    public void changelocation(ConsultRecord consultRecord) {
        UpdateWrapper<ConsultRecord> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",consultRecord.getId()).set("consult_location",consultRecord.getConsultLocation());
        consultRecordMapper.updateById(consultRecord);
    }

    @Override
    public void changeteacher(ConsultRecord consultRecord) {
        UpdateWrapper<ConsultRecord> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",consultRecord.getId()).set("consult_teacher",consultRecord.getConsultTeacher());
        consultRecordMapper.updateById(consultRecord);
    }

    @Override
    public int matchTacherandStudent(ConsultRecord consultRecord) {
        List<ConsultRecord> consultRecordList=consultRecordMapper.match(consultRecord);
        if(consultRecordList.isEmpty())
            return 1;
        else
            return 0;
    }

//
//    @Override
//    public List<ConsultRecord> getAllPendingRecord() {
//        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
//        return consultRecordMapper.selectList(queryWrapper);
//    }
}
