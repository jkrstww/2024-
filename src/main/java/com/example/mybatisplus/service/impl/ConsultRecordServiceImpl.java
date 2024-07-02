package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.ConsultRecord;
import com.example.mybatisplus.mapper.ConsultRecordMapper;
import com.example.mybatisplus.model.dto.ConsultRecordDTO;
import com.example.mybatisplus.model.dto.ConsultRecordSearchDTO;
import com.example.mybatisplus.model.dto.ConsultTeacherDTO;
import com.example.mybatisplus.model.dto.PageDTO;
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
    public List<ConsultRecordDTO> consultantCheckRecord(ConsultRecordSearchDTO consultRecordSearchDTO) {
        Integer pageNo = consultRecordSearchDTO.getPageNo();
        Integer pageSize = consultRecordSearchDTO.getPageSize();

        return consultRecordMapper.consultantCheckRecord(consultRecordSearchDTO, (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public Integer consultantCheckRecordTotal(ConsultRecordSearchDTO consultRecordSearchDTO) {
        return consultRecordMapper.consultantCheckRecordTotal(consultRecordSearchDTO);
    }

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

    @Override
    public List<ConsultTeacherDTO> getConsultTimeEcharts() {
        return consultRecordMapper.getConsultTimeEcharts();
    }

    @Override
    public Page<ConsultRecord> getMyListPage(String sn, PageDTO pageDTO) {
        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id",sn);

        Page<ConsultRecord> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());

        return consultRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<ConsultRecord> getRequest(PageDTO pageDTO) {
        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("approved_status", "待批准");
        Page<ConsultRecord> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());

        return consultRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<ConsultRecord> queryPage(ConsultRecord consultRecord, PageDTO pageDTO) {
        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
        if (consultRecord.getSId() != null && !consultRecord.getSId().isEmpty()) {
            queryWrapper.eq("s_id", consultRecord.getSId());
        }
        queryWrapper.eq("approved_status", "已批准").orderByDesc("id");

        Page<ConsultRecord> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());

        return consultRecordMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean teacherConflict(ConsultRecord consultRecord) {
        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("consult_teacher", consultRecord.getConsultTeacher())
                    .eq("consult_time", consultRecord.getConsultTime());

        List<ConsultRecord> consultRecordList=consultRecordMapper.selectList(queryWrapper);
        return !consultRecordList.isEmpty();
    }

    @Override
    public Integer consultantQueryRecordTotal(ConsultRecordSearchDTO consultRecordSearchDTO) {
        return consultRecordMapper.consultantQueryRecordTotal(consultRecordSearchDTO);
    }

    @Override
    public List<ConsultRecordDTO> consultantQueryRecord(ConsultRecordSearchDTO consultRecordSearchDTO) {
        Integer pageNo = consultRecordSearchDTO.getPageNo();
        Integer pageSize = consultRecordSearchDTO.getPageSize();

        return consultRecordMapper.consultantQueryRecord(consultRecordSearchDTO, (pageNo-1)*pageSize, pageSize);
    }

    @Override
    public boolean locationConflict(ConsultRecord consultRecord) {
        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("consult_location", consultRecord.getConsultLocation())
                .eq("consult_time", consultRecord.getConsultTime());

        List<ConsultRecord> consultRecordList=consultRecordMapper.selectList(queryWrapper);
        return !consultRecordList.isEmpty();
    }

//
//    @Override
//    public List<ConsultRecord> getAllPendingRecord() {
//        QueryWrapper<ConsultRecord> queryWrapper = new QueryWrapper<>();
//        return consultRecordMapper.selectList(queryWrapper);
//    }
}
