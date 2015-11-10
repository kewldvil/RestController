package com.restcontroller.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.restcontroller.app.entities.UserDto;
import com.restcontroller.app.service.IUserService;
import com.restcontroller.app.userserviceimplement.UserDao;

@Controller
public class MainController {
	@Autowired
	IUserService userservice;

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/")
	public String index(ModelMap model) {
		model.addAttribute("list", userservice.listUser());
		return "listUser";
	}

	@RequestMapping(value = "/addUserNSearch", method = RequestMethod.POST)
	public String addUserNSearch(ModelMap model, @RequestParam("btnAddNSearch") String btn,@RequestParam(value="txtSearch",required=false) String searchName) {
		if (btn.equals("Add New")) {
			model.addAttribute("btn","Add");
			model.addAttribute("action","addUserAction");
			model.addAttribute("formTitle","New User - Form");
			return "adduser";
		} else {
			model.addAttribute("list",userservice.searchUser(searchName));
			return "listUser";
		}

	}

	@RequestMapping(value = "/addUserAction", method = RequestMethod.POST)
	public String addUserAction(@RequestParam("file") MultipartFile file, HttpServletRequest request, ModelMap model,
			@ModelAttribute("users") UserDto users) {
		String filename = file.getOriginalFilename();

		if (!file.isEmpty()) {

			try {

				// filename = filename+ "-"+".jpg";

				byte[] bytes = file.getBytes();

				// creating the directory to store file
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
				System.out.println(savePath);
				File path = new File(savePath);
				if (!path.exists()) {
					path.mkdir();
				}

				// creating the file on server
				File serverFile = new File(savePath + File.separator + filename);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				users.setImageURL(filename);

				System.out.println(serverFile.getAbsolutePath());
				System.out.println("You are successfully uploaded file " + filename);

			} catch (Exception e) {
				System.out.println("You are failed to upload " + filename + " => " + e.getMessage());
			}
		} else {
			users.setImageURL("Null");
			System.out.println("You are failed to upload " + filename + " because the file was empty!");
		}
		userservice.insertUser(users);
		return "redirect:/";
	}
	@RequestMapping(value="/viewUpdateDeleteUser",method=RequestMethod.POST)
	public String userAction(ModelMap model,@RequestParam("userId") int id,@RequestParam("btnViewUpdateDelete") String btn){
		if(btn.equals("View")){
			return "viewUser";
		}else if(btn.equals("Update")){
			model.addAttribute("btn","Save");
			model.addAttribute("action","updateUserAction");
			model.addAttribute("formTitle","Update User - Form");
			model.addAttribute("user",userservice.getUserById(id));
			return "adduser";
		}else{
			userservice.deleteUser(id);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/updateUserAction",method=RequestMethod.POST)
	public String updateUserAction(@RequestParam("file") MultipartFile file, HttpServletRequest request, ModelMap model,@RequestParam("btnAddNCancel") String btn,
			@ModelAttribute("users") UserDto users){
		System.out.println(users.getImageURL());
		if(btn.equals("Cancel")){
			return "redirect:/";
		}else{
		String filename = file.getOriginalFilename();

		if (!file.isEmpty()) {

			try {

				// filename = filename+ "-"+".jpg";

				byte[] bytes = file.getBytes();

				// creating the directory to store file
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
				System.out.println(savePath);
				File path = new File(savePath);
				if (!path.exists()) {
					path.mkdir();
				}

				// creating the file on server
				File serverFile = new File(savePath + File.separator + filename);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				users.setImageURL(filename);

				System.out.println(serverFile.getAbsolutePath());
				System.out.println("You are successfully uploaded file " + filename);

			} catch (Exception e) {
				System.out.println("You are failed to upload " + filename + " => " + e.getMessage());
			}
		} else {
			users.setImageURL("Null");
			System.out.println("You are failed to upload " + filename + " because the file was empty!");
		}
		userservice.updateUser(users);
		return "redirect:/";
		}
	}
	// @RequestMapping(value="/test",method=RequestMethod.POST)
	// public String test(@ModelAttribute("users") UserDto users){
	// System.out.println(users.getBirthdate());
	// System.out.println(users.getRegisterDate());
	// userservice.insertUser(users);
	// System.out.println("olo");
	// return "redirect:/";
	// }
}
