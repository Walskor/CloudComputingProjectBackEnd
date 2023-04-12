package com.cloud.project.service.impl;

import com.cloud.project.common.ErrorCode;
import com.cloud.project.common.ResponseEntity;
import com.cloud.project.dao.UserDao;
import com.cloud.project.entity.User;
import com.cloud.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<User> register(User user) {
        int affectedRows = userDao.insert(user);
        if (affectedRows == 1){
            user.setPassword(null); // 清除密码，不返回给前端。在实际项目中，你应该使用密码散列和加盐等安全措施。
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.error(ErrorCode.RegisterFailed, "UserName Existed!",null);

    }

    @Override
    public ResponseEntity<User> login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null){
            ResponseEntity.error(ErrorCode.LoginFailed,"UserName does not exist!",null);
        }
        if (user.getPassword().equals(password)) {
            user.setPassword(null); // 清除密码，不返回给前端
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.error(ErrorCode.LoginFailed,"Wrong Password!",null);
    }
}
