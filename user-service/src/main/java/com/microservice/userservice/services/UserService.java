package com.microservice.userservice.services;

import com.microservice.userservice.models.User;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.valueObjects.Department;
import com.microservice.userservice.valueObjects.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO valueObject = new ResponseTemplateVO();

        User user = userRepository.findUserById(userId);
        Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(), Department.class);

        valueObject.setUser(user);
        valueObject.setDepartment(department);
        return valueObject;
    }
}
