package com.app.socialLogin.service;

import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;

public interface GoogleService {
	
	 String googleLogin();
	   String  getGoogleAccessToken(String code);
	   public GoogleUserInfo getGoogleUserProfile(String accessToken);

}
