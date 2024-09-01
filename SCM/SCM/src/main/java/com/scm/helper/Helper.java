package com.scm.helper;



import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;


public class Helper {

    private static final String OAUTH2_GOOGLE = "google";
    private static final String OAUTH2_GITHUB = "github";
    private static final String OAUTH2_FACEBOOK = "facebook";

    public static String getEmailOfLoggedInUser(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            String oAuthClient = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
            if(oAuthClient.equalsIgnoreCase(OAUTH2_GOOGLE)){
                return defaultOAuth2User.getAttribute("email").toString();
            }else if(oAuthClient.equalsIgnoreCase(OAUTH2_GITHUB)){
                return defaultOAuth2User.getAttribute("email") != null ? defaultOAuth2User.getAttribute("email").toString()
                                        : defaultOAuth2User.getAttribute("login")+"@github.com";
            }else if(oAuthClient.equalsIgnoreCase(OAUTH2_FACEBOOK)){
    
            }
        }
        return authentication.getName().toString();
    }

    public static String getEmailVerificationLink(String emailToken){
        String link = "http://localhost:8080/auth/verify-email?token="+emailToken;
        return link;

    }
}
