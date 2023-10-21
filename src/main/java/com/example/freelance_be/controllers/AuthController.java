package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.auth.AuthenticationRequestBody;
import com.example.freelance_be.dto.response.auth.AuthenticationResponseBody;
import com.example.freelance_be.services.oauth.impl.OauthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final OauthService oauthService;

    public AuthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

//    @GetMapping("")
//    public String authentication(@RequestParam String code, @RequestParam String state){
//        System.out.println(code);
//        System.out.println(state);
//        oauthService.authenticationWithGoogleCode(code);
//        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().claim("name", "phucnq").build();
//        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
//        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);
//        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
//    }

    @PostMapping("")
    public ResponseEntity<AuthenticationResponseBody> googleAuthentication(@RequestBody AuthenticationRequestBody body) throws GeneralSecurityException, IOException {
        System.out.println(body.getTokenId());
        return ResponseEntity.ok().body(oauthService.verifyIdToken(body));
    }
}
