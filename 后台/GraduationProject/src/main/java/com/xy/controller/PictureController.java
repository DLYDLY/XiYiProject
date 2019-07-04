package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author GuoZhengYing
 * @ClassName PictureController
 * @Description TDDO
 * @Date 2019/5/21 15:03
 * @Version 1.0
 */
@Api("图片上传接口")
@RestController
@RequestMapping("/pic")
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @ApiOperation(value = "上传", notes = "图片上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public E3Result fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile) {
        System.out.println(uploadFile);
        try {
            //1、取文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //2、创建一个FastDFS的客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/fastdfs-client.conf");
            //3、执行上传处理
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //4、拼接返回的url和ip地址，拼装成完整的url
            String url = IMAGE_SERVER_URL + path;
            return E3Result.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500, "图片上传失败");
        }
    }
}
