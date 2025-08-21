//package org.wujunyang.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.wujunyang.Utils.AliyunOSSOperator;
//import org.wujunyang.pojo.Result;
//
//@Slf4j
//@RestController
//public class UploadController {
////    @GetMapping("/upload")
////    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
////        log.info("接收参数{},{},{}"  ,name,age,file);
////        //获取原始文件名
////        String originalFilename = file.getOriginalFilename();
//////        获取文件后缀
////        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
////
////        // 生成新的文件名
////        String newFileName = UUID.randomUUID().toString()+extension;
////
////
////        file.transferTo(new File("D:/txt/" + newFileName));
////        return Result.success();
////    }
//
//    @Autowired
//    private AliyunOSSOperator aliyunOSSOperator;
//    @PostMapping("/upload")
//    public Result upload(MultipartFile file) throws Exception {
//        log.info("文件上传{}"  ,file.getOriginalFilename());
//        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
//        log.info("文件上传oss{}"  ,url);
//        return Result.success(url);
//    }
//}
