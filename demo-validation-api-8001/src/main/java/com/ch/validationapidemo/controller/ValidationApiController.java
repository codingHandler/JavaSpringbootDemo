package com.ch.validationapidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.common.domain.Result;
import com.ch.validationapidemo.entity.User;
import com.ch.validationapidemo.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @className: ValidationApiController
 * @Auther: ch
 * @Date: 2022/2/26 16:05
 * @Description: validation-api 使用测试Controller
 */
@RestController
@Slf4j
public class ValidationApiController {

    @Resource
    private IUserService userService;

    /**
     * test1
     */
    @PostMapping("/saveUser1")
    public Result<?> saveUser1(@RequestBody @Validated User user) {
        int add = userService.add(user);
        return Result.success(add > 0 ? "添加成功。" : "添加失败");
    }

    /**
     * test2
     */
    @PostMapping("/saveUser2")
    public Result<?> saveUse2(@RequestBody @Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                log.info("属性：{},{}", fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Result.error("fail");
        }
        int add = userService.add(user);
        return Result.success(add > 0 ? "添加成功。" : "添加失败");
    }

    /**
     * test2
     */
    @PostMapping("/selectAll")
    public Result<?> selectAll(@RequestBody User user) {
        return Result.OK(userService.add(user));
    }

    @PostMapping("/test1")
    public String test1(@RequestBody JSONObject jsonObject ,HttpServletRequest request){
        try {
            System.out.println(jsonObject.toJSONString());
            System.out.println(jsonObject);
            System.out.println("request============");
            System.out.println(request.getServletContext());
            System.out.println(request.getHeader("Accept"));
            System.out.println(request.getHeader("Content-type"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "OK";
    }



}
