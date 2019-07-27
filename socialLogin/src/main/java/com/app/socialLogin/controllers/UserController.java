package com.app.socialLogin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.app.socialLogin.Model.UserInfo;
import com.app.socialLogin.service.FacebookServiceimpl;

@Controller
public class UserController {
 
	@Autowired
 private FacebookServiceimpl facebookservice;
 
	 @GetMapping("/facebooklogin")
	 public RedirectView getFacebook()
	 {
		 System.out.println("hiii");
		 RedirectView view=new RedirectView();
		 String url=facebookservice.facebookLogin();
		 System.out.println("hii"+url);
		 view.setUrl(url);
		 return view;
	 }
	 @GetMapping("/facebook")
	 public String facebook(@RequestParam("code") String code)
	 {
		 System.out.println("/inside facebook");
		String acessToken=facebookservice.getFacebookAccessToken(code);
		 return "redirect:/facebookprofiledata/"+acessToken;
	 }
	 
	 @GetMapping("/facebookprofiledata/{accessToken:.+}")
	 public String facebookProfileData(@PathVariable String accessToken,Model model) 
	 {
		 System.out.println("/facebookProfileData facebook");
		
		User user=facebookservice.getFacebookUserProfile(accessToken);
		System.err.println(user.getFirstName()+user.getMiddleName()+user.getLastName()+user.getAbout()+user.getAbout()+user.getBio()+user.getBirthday()+user.getEmail()+user.getEducation()+user.getGender()+user.getCover()+user.getReligion());
		 UserInfo userInfo=new UserInfo();
		 userInfo.setFirstName(user.getFirstName());
		 userInfo.setLastName(userInfo.getLastName());
		 userInfo.setEmail(user.getEmail());
		
		 model.addAttribute("user",userInfo);
		 return "UserProfile";
	 }
	 

	 
 }
 

 



