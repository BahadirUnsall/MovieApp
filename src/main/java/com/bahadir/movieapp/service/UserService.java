package com.bahadir.movieapp.service;

import com.bahadir.movieapp.dto.request.RegisterRequestDto;
import com.bahadir.movieapp.entity.User;
import com.bahadir.movieapp.mapper.IUserMapper;
import com.bahadir.movieapp.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;

    public void save(User user) {
        repository.save(user);
    }

    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    public List<User> findAllByOrderByName() {
        return repository.findAllByOrderByName();
    }
    public List<User> findAllByNameLike(String name) {
        return repository.findAllByNameLike("%"+name+"%");
    }
    public List<User> findAllByEmailEndsWith(String email) {
        return repository.findAllByEmailEndsWith(email);
    }
    public Optional<User> findByEmailAndPassword(String email,String password) {
        return repository.findByEmailAndPassword(email,password);
    }
    public Boolean existsByEmailAndPassword(String email,String password) {
        return repository.existsByEmailAndPassword(email,password);
    }
    public List<User> passwordLongerThanNativeQuery(Integer value) {
        return repository.passwordLongerThanNativeQuery(value);
    }
    public List<User> passwordLongerThanJPQL(Integer value) {
        return repository.passwordLongerThanJPQL(value);
    }

    public User register(RegisterRequestDto dto) {
        User user = null;
        if (!repository.existsByEmail(dto.getEmail())){
            if (dto.getPassword().equals(dto.getRePassword())){

                user = IUserMapper.INSTANCE.dtoToUser(dto);

                repository.save(user);
            }else {
                throw new RuntimeException("Şifreler uyuşmuyor!");
            }
        }else {
            throw new RuntimeException("Mail adresi zaten kullanılıyor");
        }
        return user;
    }
}
