package com.chris.controller;

import com.chris.pojo.*;
import com.chris.service.FileService;
import com.chris.service.UserFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private UserFileService userFileService;

    @RequestMapping("/cloudMain")
    public String cloudMain(HttpServletRequest request,Model model) throws Exception {
        HttpSession httpSession = request.getSession();
        List<FileCustom> fileList = fileService.selectFileByUser(((UserCustom) httpSession.getAttribute("user")).getId());
        model.addAttribute("fileList", fileList);
        return "/cloudMainPage";
    }

    @RequestMapping(value = "/listAllFile")
    public String listAllFile(Model model) throws Exception {
        model.addAttribute("fileList", fileService.selectAllFile());
        return "/searchPage";
    }

    @RequestMapping(value = "/searchFile", method = {RequestMethod.POST})
    public String searchFile(FileQueryVo fileQueryVo, Model model) throws Exception {
        model.addAttribute("keyName", fileQueryVo.getKeyName());
        model.addAttribute("fileList", fileService.selectFileByKeyName(fileQueryVo));
        return "/searchPage";
    }

    @RequestMapping(value = "/listUserFileByType")
    public String listUserFileByType(@RequestParam("type") String type, Model model, HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession();
        List<FileCustom> allFileList = fileService.selectFileByUser(((UserCustom) httpSession.getAttribute("user")).getId());
        List<FileCustom> typeFileList = getFileByType(allFileList, type);
        model.addAttribute("fileList", typeFileList);
        return "/cloudMainPage";
    }

    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
    public @ResponseBody boolean uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
        if (file != null) {
            //文件原始名称
            String originalFileName = file.getOriginalFilename();
            //文件后缀名
            String type = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
            //文件新的名称
            String realFileName = makeFileName(originalFileName);
            //上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
            String savePath = makePath(realFileName, httpServletRequest.getServletContext().getRealPath("/WEB-INF/upload"));
            //文件大小
            long realSize = file.getSize();
            //文件大小格式化
            String formatSize = format(realSize);
            //上传时间
            Date date = new Date();
            //File既可以代表文件也可以代表目录
            File createFile = new File(savePath);
            //如果目录不存在
            if(!createFile.exists()){
                //创建目录
                createFile.mkdirs();
            }
            //内存中的文件写入磁盘
            file.transferTo(createFile);
            //创建fileQueryVo对象
            FileCustom fileCustom = new FileCustom();
            fileCustom.setFileName(realFileName);
            fileCustom.setFilePath(savePath);
            fileCustom.setRealSize((int)realSize);
            fileCustom.setFormatSize(formatSize);
            fileCustom.setUploadTime(date);
            fileCustom.setFileType(type);
            FileQueryVo fileQueryVo = new FileQueryVo();
            fileQueryVo.setFileCustom(fileCustom);
            //写入文件表
            fileService.insertFile(fileQueryVo);
            //从数据库取文件信息
            fileCustom = fileService.selectFileByFileName(fileQueryVo);
            //创建userFileQueryVo对象
            UserFileCustom userFileCustom = new UserFileCustom();
            userFileCustom.setUserId(((UserCustom) httpServletRequest.getSession().getAttribute("user")).getId());
            userFileCustom.setFileId(fileCustom.getId());
            UserFileQueryVo userFileQueryVo = new UserFileQueryVo();
            userFileQueryVo.setUserFileCustom(userFileCustom);
            //写入用户文件表
            userFileService.insertUserFile(userFileQueryVo);

            return true;
        }
        return false;
    }

    @RequestMapping(value = "/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileId") Integer fileId) throws Exception {
        FileCustom fileCustom = fileService.selectFileById(fileId);
        if (fileCustom == null) {
            return null;
        }
        File file = new File(fileCustom.getFilePath());
        if (!file.exists()) {
            return null;
        }

        //处理文件名
        String realFileName = URLEncoder.encode(fileCustom.getFileName().substring(fileCustom.getFileName().indexOf('_') + 1), "UTF-8");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", realFileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
    }

    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

    private String makePath(String filename,String dir){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String savePath = dir + "\\" + dir1 + "\\" + dir2 + "\\" + filename;  //upload\2\3  upload\3\5

        return savePath;
    }

    private String format(long size){
        float fsize = size;
        String fileSizeString;
        if (fsize < 1024) {
            fileSizeString = String.format("%.2f", fsize) + "B"; //2f表示保留两位小数
        } else if (fsize < 1048576) {
            fileSizeString = String.format("%.2f", fsize/1024) + "KB";
        } else if (fsize < 1073741824) {
            fileSizeString = String.format("%.2f", fsize/1024/1024) + "MB";
        } else if (fsize < 1024 * 1024 * 1024) {
            fileSizeString = String.format("%.2f", fsize/1024/1024/1024) + "GB";
        } else {
            fileSizeString = "0B";
        }
        return fileSizeString;
    }

    private List<FileCustom> getFileByType(List<FileCustom> fileList, String type) throws Exception {
        List<FileCustom> documentFileList = new ArrayList<>();
        List<FileCustom> imageFileList = new ArrayList<>();
        List<FileCustom> audioFileList = new ArrayList<>();
        List<FileCustom> videoFileList = new ArrayList<>();
        List<FileCustom> zipFileList = new ArrayList<>();
        List<FileCustom> otherFileList = new ArrayList<>();
        for (FileCustom f: fileList
             ) {
            switch (f.getFileType()) {
                case "txt":
                    documentFileList.add(f);
                    break;
                case "xls":
                    documentFileList.add(f);
                    break;
                case "xlsx":
                    documentFileList.add(f);
                    break;
                case "doc":
                    documentFileList.add(f);
                    break;
                case "docx":
                    documentFileList.add(f);
                    break;
                case "ppt":
                    documentFileList.add(f);
                    break;
                case "pptx":
                    documentFileList.add(f);
                    break;
                case "zip":
                    zipFileList.add(f);
                    break;
                case "rar":
                    zipFileList.add(f);
                    break;
                case "7z":
                    zipFileList.add(f);
                    break;
                case "mp3":
                    audioFileList.add(f);
                    break;
                case "cda":
                    audioFileList.add(f);
                    break;
                case "wav":
                    audioFileList.add(f);
                    break;
                case "mp4":
                    videoFileList.add(f);
                    break;
                case "avi":
                    videoFileList.add(f);
                    break;
                case "mov":
                    videoFileList.add(f);
                    break;
                case "wmv":
                    videoFileList.add(f);
                    break;
                case "3pg":
                    videoFileList.add(f);
                    break;
                case "mkv":
                    videoFileList.add(f);
                    break;
                case "flv":
                    videoFileList.add(f);
                    break;
                case "m4v":
                    videoFileList.add(f);
                    break;
                case "jpg":
                    imageFileList.add(f);
                    break;
                case "jpeg":
                    imageFileList.add(f);
                    break;
                case "gif":
                    imageFileList.add(f);
                    break;
                case "png":
                    imageFileList.add(f);
                    break;
                case "bmp":
                    imageFileList.add(f);
                    break;
                default:
                    otherFileList.add(f);
                    break;
            }
        }
        switch (type) {
            case "document":
                return documentFileList;
            case "image":
                return imageFileList;
            case "audio":
                return audioFileList;
            case "video":
                return videoFileList;
            case "zip":
                return zipFileList;
            case "other":
                return otherFileList;
            default:
                return null;
        }
    }

}
