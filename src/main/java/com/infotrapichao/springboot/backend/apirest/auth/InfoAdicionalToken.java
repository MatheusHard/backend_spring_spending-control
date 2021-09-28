package com.infotrapichao.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.infotrapichao.springboot.backend.apirest.entity.Usuario;
import com.infotrapichao.springboot.backend.apirest.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			Map<String, Object> info = new HashMap<String, Object>();
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			
			
			info.put("info_adicional", "Hello Mr.".concat(authentication.getName()));
			
			info.put("nome_usuario", usuario.getNome());
			info.put("email_usuario", usuario.getEmail());
						
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
			
			return accessToken;
	}

}



















