package com.ch.validationapidemo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @className: User
 * @Auther: ch
 * @Date: 2022/2/26 16:23
 * @Description: User测试实体类，参考文章地址：
 * https://blog.csdn.net/justry_deng/article/details/86571671
 */
@Data
public class User {
    private Long id;

    @NotBlank(message = "用户名不可为空")
    @Length(min = 3, max = 10, message = "用户名长度为3-10位")
    private String username;

    @NotBlank(message = "邮箱不可为空")
    @Email
    private String email;

    @NotBlank(message = "手机号不可为空")
    //@Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$", message = "手机号格式不正确")
    @Pattern(regexp = "^(13|14|15|16|17|18|19)[0-9]{9}", message = "手机号格式不正确")
    private String phone;

    @Min(value = 18, message = "年龄最小为18")
    @Max(value = 200, message = "年龄最大为200")
    private int age;

    @NotBlank(message = "url不可为空")
    @Length(min = 6, max = 12, message = "url长度为6到12位")
    private String url;
}
