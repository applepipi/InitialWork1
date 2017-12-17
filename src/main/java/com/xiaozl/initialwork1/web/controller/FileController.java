package com.xiaozl.initialwork1.web.controller;

import com.xiaozl.initialwork1.exception.BusinessException;
import com.xiaozl.initialwork1.exception.InitialWorkException;
import com.xiaozl.initialwork1.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * Created by user on 2017/12/12.
 * 文件上传下载
 */
@Controller
@RequestMapping(value = "/fileOperate")
public class FileController {

    //文件上传
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile[] multipartFiles, HttpServletRequest request)throws Exception{
        //保存路径，webapp下的uploadTempDirectory
        File savePath = new File(request.getSession().getServletContext().getRealPath("/uploadTempDirectory"));
        //目录不存在
        if(!savePath.exists()){
            savePath.mkdir();
        }
        if(null != multipartFiles){
            for(MultipartFile multipartFile: multipartFiles){
                try {
                    multipartFile.transferTo(new File(savePath,multipartFile.getOriginalFilename()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //throw new MaxUploadSizeExceededException(111111111111L);异常测试
        return "redirect:/index";
    }
    //文件下载
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile() throws Exception {
        File file = new File("E:\\test.txt");
        if(!file.exists()){
            throw new BusinessException(InitialWorkException.FileNotFound.getMessage());
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachement",file.getName());
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }
}
