package com.mybookmark;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mybookmark.connections.DataStaxAstraProperties;
import com.mybookmark.controller.User; 

@SpringBootApplication
@Controller
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class MybookmarksAppApplication extends WebSecurityConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(MybookmarksAppApplication.class, args);
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
		Map<String, String> obj = Collections.singletonMap("login", principal.getAttribute("login"));
		User user = new User();
		user.setName(obj.get("login"));
		model.addAttribute("name", user );
		return "user";
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(
				a -> a
				//.antMatchers("/","/error", "/webjars/**").permitAll()
				.anyRequest().permitAll()
				)
				.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.logout(l->l.logoutSuccessUrl("/").permitAll())
				.csrf(c-> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.oauth2Login();
	}
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle); 
		
	}
}
