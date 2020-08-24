package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.shared.UserRequestModel;
import com.example.demo.shared.UserResponseModel;
import java.util.Optional;

public interface UserService {
	public UserResponseModel createNewUser(UserRequestModel userRequestModel);
	public List<UserResponseModel> getAllUsers();
	public UserResponseModel findUserById(String userId);

}
