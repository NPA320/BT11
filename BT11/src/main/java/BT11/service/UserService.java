package BT11.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BT11.entity.UserInfo;
import BT11.repository.UserInfoRepository;

@Service
public record UserService(UserInfoRepository repository,
                          PasswordEncoder passwordEncoder) {
	
    public String addUser(UserInfo userInfo) {
        // mã hoá password trước khi lưu
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "Thêm user thành công!";
    }
}
