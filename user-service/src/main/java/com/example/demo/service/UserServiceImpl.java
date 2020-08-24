package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.shared.UserRequestModel;
import com.example.demo.shared.UserResponseModel;
import org.modelmapper.TypeToken;

@Service
public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	private ModelMapper modelMapper;

//	public UserServiceImpl(UserDao userDao, ModelMapper modelMapper) {
//        this.userDao = userDao;
//        this.modelMapper = modelMapper;
//   }
	public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        modelMapper = new ModelMapper();
    }

	@Override
    @Transactional
    public UserResponseModel createNewUser(UserRequestModel userRequestModel) {
        String str=UUID.randomUUID().toString();
//        String str1[]=str.split("-");
        userRequestModel.setUserId(str);
        //userRequestModel.setUserId(str);
//        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user=userDao.save(modelMapper.map(userRequestModel,User.class));
        return modelMapper.map(user,UserResponseModel.class);

    }
    
	@Override
    @Transactional
    public List<UserResponseModel> getAllUsers() {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<UserResponseModel>>(){}.getType();
        List<UserResponseModel> postDtoList = modelMapper.map(userDao.findAll(),listType);
        return postDtoList;
    }
	
	 @Override
     @Transactional
     public UserResponseModel findUserById(String userId) {
         User user=userDao.findByUserId(userId);

        return modelMapper.map(user, UserResponseModel.class);
     }
}
