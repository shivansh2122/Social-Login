package com.app.socialLogin.service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceimpl implements FacebookService {
    
	@Value("${spring.social.facebook.appId}")
	private String facebookId;
	@Value("${spring.social.facebook.appSecret}")
	private String facebookSecret;
	
	private FacebookConnectionFactory createFacebookConnection()
	{
		return new FacebookConnectionFactory(facebookId,facebookSecret);
	}
	
	 @Override
	public String facebookLogin() {
		// TODO Auto-generated method stub
		System.out.println("hiii"+facebookId+"="+facebookSecret);
		OAuth2Parameters parameter=new OAuth2Parameters();
		parameter.setRedirectUri("http://localhost:4545/facebook");
		parameter.setScope("public_profile,email");
		return createFacebookConnection().getOAuthOperations().buildAuthenticateUrl(parameter);
	}
	   @Override
	   public String  getFacebookAccessToken(String code)
	        {
		   System.out.println("/getFacebookAccessToken");
	        return 	createFacebookConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:4545/facebook", null).getAccessToken();
	        }
	   @Override 
	   public User getFacebookUserProfile(String accessToken) 
	   {
        Facebook facebook=new FacebookTemplate(accessToken);
        String field[]= { "id", "age_range", 
                "last_name","middle_name","first_name"};
    /*    byte[] profileImage = facebook.userOperations().getUserProfileImage();
        ByteArrayInputStream bis = new ByteArrayInputStream(profileImage);
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("../Documents/ineotrust/output.jpg") );
        System.out.println("image created");*/
        return facebook.fetchObject("me",User.class,field);
	   }
	   
	 
}