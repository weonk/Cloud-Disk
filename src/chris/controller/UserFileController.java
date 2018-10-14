package com.chris.controller;

import com.chris.pojo.UserFileQueryVo;
import com.chris.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserFileController {
    @Autowired
    private UserFileService userFileService;

    @RequestMapping(value = "/deleteUserFile", method = {RequestMethod.POST})
    public @ResponseBody boolean deleteUserFile(UserFileQueryVo userFileQueryVo) throws Exception {
        if (userFileService.deleteUserFileByUserIdAndFileId(userFileQueryVo) == 1) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/collectionFile", method = {RequestMethod.POST})
    public @ResponseBody boolean collectionFile(UserFileQueryVo userFileQueryVo) throws Exception {
        if (userFileService.selectUserFileByUserIdAndFileId(userFileQueryVo) != null) {
            return false;
        } else {
            userFileService.insertUserFile(userFileQueryVo);
            return true;
        }
    }
}
