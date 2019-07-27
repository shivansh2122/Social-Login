package com.app.socialLogin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.app.socialLogin.Model.UserInfo;
import com.app.socialLogin.service.GoogleServiceimp;

@Controller
public class GoogleController {
	
	
	@Autowired private GoogleServiceimp GoogleServiceimpl;
	@GetMapping("/googleLogin")
	public RedirectView googleLogin()
	{
		RedirectView redirectView=new RedirectView();
		String url=GoogleServiceimpl.googleLogin();
		redirectView.setUrl(url);
		return redirectView;
	}
	
	
	 @GetMapping("/google")
	 public String facebook(@RequestParam("code") String code)
	 {
		 System.out.println("COde"+code);
		 System.out.println("/inside google");
		String acessToken=GoogleServiceimpl.getGoogleAccessToken(code);
		 return "redirect:/googleprofiledata/"+acessToken;
	 }
	
	@GetMapping("/googleprofiledata/{accessToken:.+}")
	public String getProfileData(@PathVariable String accessToken,Model model)
	{
		System.out.println("HIi");
		GoogleUserInfo info=GoogleServiceimpl.getGoogleUserProfile(accessToken);
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(info.getFirstName());
		userInfo.setLastName(info.getLastName());
		userInfo.setEmail(info.getEmail());
		userInfo.setImageUrl(info.getProfilePictureUrl());
		System.err.println(userInfo.getFirstName()+userInfo.getEmail()+userInfo.getImageUrl());
		model.addAttribute("user",userInfo);
		return "UserProfile";
		
		
	}

}
