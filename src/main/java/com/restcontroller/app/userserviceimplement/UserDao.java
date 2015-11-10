package com.restcontroller.app.userserviceimplement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.restcontroller.app.entities.UserDto;
import com.restcontroller.app.service.IUserService;

@Repository
public class UserDao implements IUserService {
	@Autowired
	private DataSource dataSource;

	@Override
	public boolean insertUser(UserDto s) {
		String sql = "INSERT INTO tbuser(username,email,password,birthdate,registerdate,image) VALUES(?,?,?,?,?,?)";
		try (
				Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) {
			ps.setString(1, s.getUsername());
			ps.setString(2, s.getEmail());
			ps.setString(3, s.getPassword());
			ps.setDate(4, s.getBirthdate());
			ps.setDate(5, s.getRegisterDate());
			ps.setString(6, s.getImageURL());
			if(ps.execute()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(UserDto users) {
		String sql = "UPDATE tbuser set email=?,username=?,password=?,birthdate=?,image=? where id=?";
		try (
				Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) 
		{
			ps.setString(1, users.getEmail());
			ps.setString(2, users.getUsername());
			ps.setString(3, users.getPassword());
			ps.setDate(4, users.getBirthdate());
			ps.setString(5, users.getImageURL());
			ps.setInt(6, users.getId());
			if(ps.executeUpdate() >0){
				return true;
			}
	
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		String sql = "DELETE FROM tbuser where id=?";
		ArrayList<UserDto> arr = new ArrayList<UserDto>();
		try (
				Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) 
		{
			ps.setInt(1, id);
			if(ps.executeUpdate() >0){
				return true;
			}
	
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<UserDto> listUser() {
		String sql = "SELECT id,username,email,password,birthdate,registerdate,image FROM tbuser";
		ArrayList<UserDto> arr = new ArrayList<UserDto>();
		UserDto s = null;
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new UserDto();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setEmail(rs.getString("email"));
				s.setPassword(rs.getString("password"));
				s.setBirthdate(rs.getDate("birthdate"));
				s.setRegisterDate(rs.getDate("registerDate"));
				s.setImageURL(rs.getString("image"));
				arr.add(s);
			}
			return arr;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserById(int id) {
		String sql = "SELECT * FROM tbuser where id=?";
		UserDto s = null;
		try (
				Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			)
		{
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			s = new UserDto();
			s.setId(rs.getInt("id"));
			s.setUsername(rs.getString("username"));
			s.setEmail(rs.getString("email"));
			s.setPassword(rs.getString("password"));
			s.setBirthdate(rs.getDate("birthdate"));
			s.setRegisterDate(rs.getDate("registerDate"));
			s.setImageURL(rs.getString("image"));
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<UserDto> searchUser(String searchName) {
			String sql = "SELECT id,username,email,password,birthdate,registerdate,image FROM tbuser where username like ?";
			ArrayList<UserDto> arr = new ArrayList<UserDto>();
			UserDto s = null;
			try (Connection con = dataSource.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);) {
				ps.setString(1, "%" + searchName + "%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					s = new UserDto();
					s.setId(rs.getInt("id"));
					s.setUsername(rs.getString("username"));
					s.setEmail(rs.getString("email"));
					s.setPassword(rs.getString("password"));
					s.setBirthdate(rs.getDate("birthdate"));
					s.setRegisterDate(rs.getDate("registerDate"));
					s.setImageURL(rs.getString("image"));
					arr.add(s);
				}
				return arr;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

