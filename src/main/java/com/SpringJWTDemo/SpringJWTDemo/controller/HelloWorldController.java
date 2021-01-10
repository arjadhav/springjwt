package com.SpringJWTDemo.SpringJWTDemo.controller;

import com.SpringJWTDemo.SpringJWTDemo.model.ResponseDTO;
import com.SpringJWTDemo.SpringJWTDemo.model.UserDtl;
import com.SpringJWTDemo.SpringJWTDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloWorldController {

	@Autowired
	UserService userService;
	
	@RequestMapping({ "/hello" })
	public String firstPage() {

		return "Hello World";
	}

	@PostMapping(path = "/create")
	public @ResponseBody
	ResponseEntity<ResponseDTO> addUser(@RequestBody UserDtl user) throws ParseException {
		userService.save(user);
		ResponseDTO responseDTO=new ResponseDTO();
		if(user.getId() != null)
			responseDTO.setInfo("User updated sucessfully");
		else
			responseDTO.setInfo("User created sucessfully");

		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}

	@RequestMapping("/getUsers")
	public ResponseEntity<List<UserDtl>> getAllUsers() {

		Iterable<UserDtl> user = userService.getAllUsers();
		List<UserDtl> target = new ArrayList<>();
		user.forEach(target::add);
		return new ResponseEntity<List<UserDtl>>(target, HttpStatus.OK);
	}

	@GetMapping(value = "/delete")
	public ResponseEntity<ResponseDTO> deleteUser(RedirectAttributes ra, @RequestParam("id") Integer id) throws ParseException {
		Optional<UserDtl> user = userService.getUserById(id);
		ResponseDTO responseDTO=new ResponseDTO();
		if(!user.isPresent())
			responseDTO.setInfo("User is not exist");
		else{
			responseDTO.setInfo("User deleted sucessfully");
			userService.deleteUserById(id);
		}
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}


}
