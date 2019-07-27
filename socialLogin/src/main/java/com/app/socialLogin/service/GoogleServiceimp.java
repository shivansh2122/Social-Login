package com.app.socialLogin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class GoogleServiceimp implements GoogleService {

	@Value("${spring.social.google.appId}")
	private String googleId;
	@Value("${spring.social.google.appSecret}")
	private String gooogleSecret;
	
	private GoogleConnectionFactory createGoogleConnection()
	{
		return new GoogleConnectionFactory(googleId, gooogleSecret);
				
	}
	


@Override
public String googleLogin() {
	System.out.println("INSIDE GOOGLE LOGIN");
	OAuth2Parameters parameter=new OAuth2Parameters();
	parameter.setRedirectUri("http://localhost:4545/google");
	parameter.setScope("profile");
	parameter.setScope("email");
	return createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameter);	
}

@Override
public String getGoogleAccessToken(String code) {
	System.out.println("INSIDE GET ACCESS TOKEN");
	return createGoogleConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:4545/google", null).getAccessToken();
}

@Override
public GoogleUserInfo getGoogleUserProfile(String accessToken) {
System.err.println("Last method mein");
	Google google=new GoogleTemplate(accessToken);
	//String field[]= {"id","first_name","last_name",}
	GoogleUserInfo info=google.userOperations().getUserInfo();
	System.out.println("Info"+info.getEmail());
	return info;
	

}
}
