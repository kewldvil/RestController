package com.restcontroller.app.service;

import java.util.ArrayList;

import com.restcontroller.app.entities.UserDto;

public interface IUserService {
	public boolean insertUser(UserDto users);
	public boolean updateUser(UserDto users);
	public boolean deleteUser(int id);
	public ArrayList<UserDto> listUser();
	public UserDto getUserById(int id);
	public ArrayList<UserDto> searchUser(String searchName);
}
