package com.zhou.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FileUpAndDownController {
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testResponseEntityDown(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/ResponseEntity实现文件下载.PNG");
        //创建输入流
        InputStream fileInputStream = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[fileInputStream.available()];
        //将流读取到数组中
        fileInputStream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=ResponseEntity实现文件下载.PNG");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        fileInputStream.close();
        return responseEntity;
    }

    @RequestMapping("/testUp")
    public String testResponseEntityUp(MultipartFile photo, HttpSession session) throws IOException {
        //获取标签栏中的name值
        System.out.println(photo.getName());
        //获取文件名
        String filename = photo.getOriginalFilename();
        System.out.println("文件名：" + filename);
        //获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //将UUID作为文件名
        //replaceAll("-","")将横线换为空字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //将UUID和后缀名拼接的结果作为最终的文件名.防止同名文件覆盖掉原文件
        filename = uuid + suffixName;
        //通过session获取ServletContext
        ServletContext servletContext = session.getServletContext();
        //通过servletContext获取服务器上传的位置
        String photoPath = servletContext.getRealPath("photo");
        //读取目录
        File file = new File(photoPath);
        //判断目录是否存在
        if (!file.exists()) {
            //若不存在，则创建目录
            boolean mkdir = file.mkdir();
            System.out.println(mkdir);
        }
        /**
         *  File.separator：分隔符
         *  finalPath：组合文件夹目录、分隔符、文件名
         */
        String finalPath = photoPath + File.separator + filename;
        photo.transferTo(new File(finalPath));
        return "index";
    }

}
