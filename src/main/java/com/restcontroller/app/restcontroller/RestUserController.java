package com.restcontroller.app.restcontroller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restcontroller.app.entities.UserDto;
import com.restcontroller.app.service.IUserService;



@RestController
@RequestMapping("/api")
public class RestUserController {

	@Autowired
	private IUserService userservice;
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listUser() {
		ArrayList<UserDto> users = userservice.listUser();
		Map<String, Object> map = new HashMap<String, Object>();
		if (users.isEmpty()) {
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			map.put("MESSAGE", "STUDENT NOT FOUND...");
			return new ResponseEntity<Map<String, Object>>(map,
					HttpStatus.NOT_FOUND);
		}
		map.put("STATUS", HttpStatus.OK.value());
		map.put("MESSAGE", "STUDENT HAS BEEN FOUNDS");
		map.put("RESPONSE_DATA", userservice.listUser());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/delete", method= RequestMethod.GET )
	public ResponseEntity<Map<String,Object>> 
				deleteStudent(@RequestParam("id") int id){
		System.out.println(id);
		Map<String, Object> map  = new HashMap<String, Object>();
		if(userservice.deleteUser(id)){
			map.put("MESSAGE","STUDENT HAS BEEN DELETED.");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.OK);
		}else{
			map.put("MESSAGE","STUDENT HAS NOT BEEN DELETED.");
			map.put("STATUS", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String,Object>>
								(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/add" , method = RequestMethod.POST )
	public ResponseEntity<String> add (UserDto b ,  @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
		
		System.out.println("insert student!");
		
		if(!file.isEmpty()){
			try{
				
				UUID uuid = UUID.randomUUID();
	            String originalFilename = file.getOriginalFilename(); 
	            String extension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	            String randomUUIDFileName = uuid.toString();
	            
	            String filename = randomUUIDFileName+"."+extension;
				
	            b.setImageURL(filename);
	            
				byte[] bytes = file.getBytes();

				// creating the directory to store file
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/");
				System.out.println(savePath);
				File path = new File(savePath);
				if(!path.exists()){
					path.mkdir();
				}
				
				// creating the file on server
				File serverFile = new File(savePath + File.separator + filename );
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				System.out.println(serverFile.getAbsolutePath());
				System.out.println("You are successfully uploaded file " + filename);
			}catch(Exception e){
				System.out.println("You are failed to upload  => " + e.getMessage());
			}
		}else{
			System.out.println("The file was empty!");
		}
		
		
		
		if(userservice.insertUser(b)){
			System.out.println("Book has been inserted successfully.....!");
			return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
				
		}
		System.out.println("Error..... cannot added book!");
		return new ResponseEntity<String>("ERROR",HttpStatus.NOT_FOUND);
		
		
	}

}
