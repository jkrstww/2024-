package com.example.mybatisplus.web.controller;


import com.deepoove.poi.XWPFTemplate;
import com.example.mybatisplus.common.utls.ExcelUtil;
import com.example.mybatisplus.mapper.VisitRecordMapper;
import com.example.mybatisplus.model.domain.Report;
import com.example.mybatisplus.model.dto.ConsultTeacherDTO;
import com.example.mybatisplus.service.FileService;
import com.example.mybatisplus.service.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {
    protected FileService fileService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private VisitRecordMapper visitRecordMapper;

    protected ResourceLoader resourceLoader;

    public FileController(FileService fileService, ResourceLoader resourceLoader) {
        this.fileService = fileService;
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> upload(MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap();
        map = fileService.upload(file);
        return ResponseEntity.ok().body(map);
    }

    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i + 1);
    }

    // 下载咨询报告表
    @RequestMapping("/downloadReport")
    public void downloadDispatchList(Long id, HttpServletResponse response) throws IOException {
        // Long id = r.getId();
        Report report = reportService.getById(id);
        File sourceFile = new File("src/main/resources/static/test.docx");

        Map<String, Object> data = new HashMap() {{
            put("sName", report.getSName());
            put("sId", report.getSId());
            put("academy", report.getSAcademy());
            put("age", report.getAge());
            put("phoneNumber", report.getSPhone());
            put("problem", report.getProblem());
            put("teacher", report.getConsultTeacher());
            put("evaluation", report.getEvaluation());
            put("createdTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(report.getCreatedTime()));
            put("times", report.getTimes());
        }};
        if (report.getSGender() == 0) {
            data.put("gender", "男");
        } else {
            data.put("gender", "女");
        }

        //浏览器端下载
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        String fileName = "咨询报告表_" + UUID.randomUUID() + ".docx";
        response.setHeader("Content-Disposition", "attachment;filename="
                .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
        response.flushBuffer();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
        //创建输出流
        OutputStream os = response.getOutputStream();
        //最终编译渲染并输出
        XWPFTemplate.compile(sourceFile).render(data).writeAndClose(os);
        byte[] buffer = new byte[1024];
        int i = bis.read(buffer);
        while (i != -1) {
            os.write(buffer, 0, i);
            i = bis.read(buffer);
        }
        if (bis != null) {
            bis.close();
        }
    }

    // 下载咨询报告表
    @RequestMapping("/downloadExcel")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<ConsultTeacherDTO> list = visitRecordMapper.getDTO();
        list.forEach(s -> {
            s.setSumTime(s.getTimes()*2);
        });

        //excel标题
        String[] title = {"初访员", "次数", "总时间"};

        //excel文件名
        String fileName = "初访员信息表" + System.currentTimeMillis() + ".xlsx";

        //sheet名
        String sheetName = "信息表";

        String [][] content = new String[list.size()][title.length];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            ConsultTeacherDTO obj = list.get(i);
            content[i][0] = obj.getName();
            content[i][1] = obj.getTimes().toString();
            content[i][2] = obj.getSumTime().toString();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
