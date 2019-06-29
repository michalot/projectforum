package com.sda.demo.service;

import com.sda.demo.entity.User;
import com.sda.demo.model.UserDto;
import com.sda.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service   // klasa z logiką biznesową
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void deleteUser(UserDto userDto) {
        User user = userRepository.findUserByLogin(userDto.getLogin())
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
        userRepository.delete(user);
    }

    public void saveUser(UserDto userDto) throws ParseException {

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String newDateFormat = sdf.format(new Date());
        userDto.setUnlockDate((sdf.parse(newDateFormat)));

        User userToSave = modelMapper.map(userDto, User.class);

        userRepository.save(userToSave);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

//        List<UserDto> usersDto = new ArrayList<>();
//        for (User user: users){
//            usersDto.add(modelMapper.map(user, UserDto.class));
//        }
        return users.stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }
}
