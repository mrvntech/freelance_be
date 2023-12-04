package com.example.freelance_be.services.oauth.impl;

import com.example.freelance_be.dto.request.auth.AuthenticationRequestBody;
import com.example.freelance_be.dto.response.auth.AuthenticationResponseBody;
import com.example.freelance_be.dto.response.auth.OauthResponseBody;
import com.example.freelance_be.entities.User;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.oauth.IOauthService;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
public class OauthService implements IOauthService {
    private final GoogleOauthConfig googleOauthConfig;
    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;

    public OauthService(GoogleOauthConfig googleOauthConfig, UserRepository userRepository, JwtEncoder jwtEncoder) {
        this.googleOauthConfig = googleOauthConfig;
        this.userRepository = userRepository;
        this.jwtEncoder = jwtEncoder;
    }

    public AuthenticationResponseBody verifyIdToken(AuthenticationRequestBody body) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(new NetHttpTransport(),new GsonFactory());
        GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(body.getTokenId());
        System.out.println(googleIdToken);
        Optional<User> existedUser = userRepository.findByEmail(body.getUsername());
        if(existedUser.isEmpty()){
            User newUser = new User();
            newUser.setEmail(body.getUsername());
            newUser.setImageUrl(body.getImageUrl());
            userRepository.save(newUser);
        }
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().claim("username", body.getUsername()).build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
        AuthenticationResponseBody responseBody = new AuthenticationResponseBody();
        responseBody.setAccessToken(jwt.getTokenValue());
        if(body.getUsername().equals("mictosieunhan@gmail.com")){
            responseBody.setRole("ADMIN");
        }else {
            responseBody.setRole("USER");
        }
        return responseBody;
    }
}
