package com.xiaozl.initialwork1.web.controller.admin;

import com.xiaozl.initialwork1.entity.User;
import com.xiaozl.initialwork1.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2017/12/7.
 * 用户管理
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    //to userList
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String toUserList(Model model, HttpServletRequest request) throws Exception {
        /*List<User> userList = Collections.emptyList();
        try{
            userList = userService.listUser();
        }catch (Exception e){
            model.addAttribute("listUser_err", "查询用户列表失败!");
        }
        model.addAttribute("userList",userList);
        return "admin/userList";*/

        List<User> userList = Collections.emptyList();
        userList = userService.listUser();
        model.addAttribute("userList", userList);
        return "admin/userList";
    }

    //deleteUser
    @RequestMapping(value = "/{userId}/del", method = RequestMethod.POST)
    public String delUser(@PathVariable("userId") Integer userId) throws Exception {
        /*try {
            userService.delUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/user/users";*/

        userService.delUser(userId);
        return "redirect:/admin/user/users";
    }
   /* ajax 还没想好怎么写
    @RequestMapping(value = "ajax/del",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject delUser(@RequestParam("userId") Integer id){
        try{

        }catch(Exception e){

        }
        return "";
    }*/

    //to userUpdate
    @RequestMapping(value = "/{userId}/update", method = RequestMethod.GET)
    public String toUserUpdate(@PathVariable("userId") Integer userId, Model model) throws Exception {
        /*User user = null;
        try {
            user = userService.queryById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        return "admin/userUpdate";*/

        User user = null;
        user = userService.queryById(userId);
        model.addAttribute("user", user);
        return "admin/userUpdate";
    }

    //updateUser
    @RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
    public String updateUser(User user, Model model) throws Exception{
        /*try {
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/user/users";*/

        userService.updateUser(user);
        return "redirect:/admin/user/users";
    }

    //文件下载
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws Exception {
        downloadExcelFile(request);
        //File file = new File("D:\\UserList.xls");
        File file = new File(request.getSession().getServletContext().getRealPath("/downloadTempDirectory/UserList.xls"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachement",file.getName());
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

    //生成用户信息表
    public void downloadExcelFile(HttpServletRequest request) throws Exception {

        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet("用户信息");
        //第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        //第四步创建单元格
        HSSFCell cell = row.createCell(0);//第一个单元格
        cell.setCellValue("用户名");//设定值
        cell.setCellStyle(style);//内容居中

        cell = row.createCell(1);//第二个单元格
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        //第五步插入数据
        List<User> list = userService.listUser();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            //创建行
            row = sheet.createRow(i+1);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(user.getUserName());
            row.createCell(1).setCellValue(user.getPassword());
        }
        //第六步将生成excel文件保存到指定路径下
        try {
            File savePath = new File(request.getSession().getServletContext().getRealPath("/downloadTempDirectory"));
            //目录不存在
            if(!savePath.exists()){
                savePath.mkdir();
            }
            FileOutputStream fo = new FileOutputStream(savePath+"/UserList.xls");
            wb.write(fo);
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel文件生成成功...");
    }
}
