package com.scm.secuitycustom;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class OAuthAuthenticateSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private UserRepo userRepo;

    private static final String OAUTH2_GOOGLE = "google";
    private static final String OAUTH2_GITHUB = "github";
    private static final String OAUTH2_FACEBOOK = "facebook";
    String email = "";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            System.out.println("***OAuth2AuthenticationToken***"+oAuth2AuthenticationToken);
            System.out.println("***oAuth2AuthenticationToken.getAuthorizedClientRegistrationId()***"+oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
        
            String oAuthClient = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(); 

            DefaultOAuth2User oAuthUser = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();

            User user = new User();
            user.setEmailVerified(true);
            user.setEnabled(true);
            user.setRoleList(List.of(AppConstants.ROLE_USER));
            user.setPassword("Password@password12345");

            if(oAuthClient.equalsIgnoreCase(OAUTH2_GOOGLE)){
                String name = oAuthUser.getAttribute("name").toString();
                String profilePic = oAuthUser.getAttribute("picture").toString();
                email = oAuthUser.getAttribute("email").toString();
                user.setName(name);
                user.setEmailId(email);
                user.setProfilePic(profilePic);
                user.setProvider(Providers.GOOGLE);
                user.setAbout("This is me "+name);
            }
            else if(oAuthClient.equalsIgnoreCase(OAUTH2_GITHUB)){
                    String name = oAuthUser.getAttribute("login");
                    System.out.println("****login****"+name);
                    String profilePic = oAuthUser.getAttribute("avatar_url");
                    email = oAuthUser.getAttribute("email") != null ? oAuthUser.getAttribute("email") : name+"@github.com";
                    user.setName(oAuthUser.getAttribute("login"));
                    user.setEmailId(email);
                    user.setProfilePic(profilePic);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("This is me "+name);
            }else if(oAuthClient.equals(OAUTH2_FACEBOOK)){

            }

            if(!userRepo.findByEmailId(email).isPresent()){
                userRepo.save(user);
            }
            //after login and before redirecting to success url need to save data in database
        /*  DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        System.out.println("authentication*****"+authentication);
        System.out.println("oauthUser.toString()---****"+oauthUser.toString());
        String name = oauthUser.getAttribute("name").toString();
        String email = oauthUser.getAttribute("email").toString();
        String profilePic = oauthUser.getAttribute("picture").toString();

        if(!userRepo.findByEmailId(email).isPresent()){
            User user = new User();
            user.setName(name);
            user.setEmailId(email);
            user.setPassword("Password@password12345");
            user.setEmailVerified(true);
            user.setEnabled(true);
            user.setProfilePic(profilePic);
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This is me "+oauthUser.getAttribute("name").toString());
            user.setRoleList(List.of(AppConstants.ROLE_USER));
            userRepo.save(user);
        }
            */
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
