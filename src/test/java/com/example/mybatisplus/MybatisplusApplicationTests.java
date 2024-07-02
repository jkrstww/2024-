package com.example.mybatisplus;

import com.alibaba.excel.EasyExcel;
import com.deepoove.poi.XWPFTemplate;
import com.example.mybatisplus.mapper.ClientMapper;
import com.example.mybatisplus.mapper.VisitRecordMapper;
import com.example.mybatisplus.mapper.VisitRequestMapper;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.model.dto.ConsultTeacherDTO;
import com.example.mybatisplus.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {
    @Autowired
    private VisitRequestMapper visitRequestMapper;
    @Autowired
    private VisitRequestService visitRequestService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private VisitRecordService visitRecordService;
    @Autowired
    private VisitRecordMapper visitRecordMapper;
    @Autowired
    private ConsultRecordService consultRecordService;
    @Autowired
    private WhitelistSettingService whitelistSettingService;

    @Test
    public void insertVisitRequest() {
        for (int i = 0; i < 10; i++) {
            VisitRequest visitRequest = new VisitRequest().setSId("2021222"+i).setIsDanger(1);
            visitRequestService.save(visitRequest);
        }
    }

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            Client client = new Client().setName("张"+i).setPassword("2021"+i).setRoleId(0).setPhoneNumber("18088888"+i);
            clientService.save(client);
        }
    }

    @Test
    public void test2() throws IOException {
        //===================使用Map的方式================================
        // Resource resource = new ClassPathResource("static/" + "poi_ti_test.docx");
        File sourceFile = new File("src/main/resources/static/test.docx");
        //构建数据
        Map<String, Object> data = new HashMap() {{
            put("sName", "一亿");
            put("sId", "2022");
            put("gender", "男");
            put("age", 18);
            put("academy", "计算机");
            put("phoneNumber", "18088888");
            put("times", 1);
            put("problem", "吃多了");
            put("teacher", "张三");
            put("evaluation", "多运动");
            put("createdTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        }};
        //创建输出流
        OutputStream os = new FileOutputStream("template1_out.docx");
        //最终编译渲染并输出
        XWPFTemplate.compile(sourceFile).render(data).writeAndClose(os);
        System.out.println("输出完毕");
    }

    @Test
    public void test3() throws IOException {
        for (int i = 0; i < 10; i++) {
            visitRecordService.save(new VisitRecord()
                    .setConclusion("无需咨询")
                    .setSId("2022")
                    .setProblem("无")
                    .setRiskRank(0)
                    .setVisitTeacher("李四")
                    .setVisitLocation("教学楼A101")
                    .setVisitTime(LocalDateTime.now())
            );
        }
    }

    @Test
    public void test4() throws IOException {
        List<ConsultTeacherDTO> list = visitRecordMapper.getDTO();
        list.forEach(s -> {
            s.setSumTime(s.getTimes()*2);
        });

        File sourceFile = new File("src/main/resources/static/test.xlsx");

        EasyExcel.write("src/main/resources/static/test.xlsx", ConsultTeacherDTO.class).sheet("模板").doWrite(list);
    }

    @Test
    public void test5() throws IOException {
        for (int i = 0; i < 12; i++) {
            consultRecordService.save(new ConsultRecord()
                    .setPhoneNumber("18812345678")
                    .setSId("2024"));
        }
    }

    @Test
    public void test6() throws IOException {
        for (int i = 0; i < 12; i++) {
            whitelistSettingService.save(new WhitelistSetting()
                    .setRoleId((long)3)
                    .setName("初访员" + i)
                    .setPassword("123")
                    .setSn("3000" + i)
                    .setPhoneNumber("1788888" + i));
        }
    }
//    @Autowired
//    private AdminService adminService;
//    @Test
//    void contextLoads() {
//        Admin byId = adminService.getById(1);
//        System.out.println(byId);
//    }

}
