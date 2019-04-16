package com.platform.controller

import com.platform.dao.domain.MusicInfo
import com.platform.dao.domain.User
import com.platform.dao.mapper.UserInfoMapper
import com.platform.service.MusicInfoService
import com.platform.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = "/cosu")
class CosuController {
    @Autowired
    MusicInfoService musicInfoService;
    @Autowired
    UserInfoService userInfoService

    @RequestMapping(value = "/index")
    public String record() {
        return "cosu/index";
    }

//    @PostMapping(value = "/index")
//    ModelAndView save(ModelAndView modelAndView, UserInfo userInfo, HttpSession httpSession){
//        userInfoService.insert(userInfo);//保存数据.
//        modelAndView.setViewName("cosu/index");
//        return modelAndView;
//    }
    @Autowired
    UserInfoMapper userMapper
    @PostMapping(value = "/savaUser")
    ModelAndView save(ModelAndView modelAndView, User user, HttpSession httpSession){
        userMapper.insert(user);
//        userMapper.delete(user.username);
//        userMapper.update(user);
        System.out.println("记录条数："+userMapper.countAll());
        modelAndView.setViewName("cosu/index");
        return modelAndView;
    }

    @RequestMapping(value = "/searchEDI", method = RequestMethod.POST)
    @ResponseBody
    public List<List<String>> searchEDI(@RequestBody Map<String, String> request){

        List<List<String>> responseBody = []
        String criteria = request.get('username').replace(";","")
        List<List<String>> searchResult = userMapper.selectAll()
        responseBody.addAll(searchResult)

        return responseBody
    }

    @RequestMapping("/music")
    @ResponseBody
    public List<MusicInfo> getMusicInfo(MusicInfo musicInfo) {
        List<MusicInfo> musicInfoList = musicInfoService.getMusicInfo(null);
        return musicInfoList;
    }
}
