package stu.cn.ua.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import stu.cn.ua.domain.User;
import stu.cn.ua.mapper.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

}
