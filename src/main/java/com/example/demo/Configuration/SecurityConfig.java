package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Autowired
 CustomUserDetailsService customUserDetailsService;

 @Bean
 public static PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	  http.csrf().disable().authorizeHttpRequests().requestMatchers("/exam.html").permitAll().requestMatchers("/add-question.html").permitAll()
	  .requestMatchers("Answer.html").permitAll()
	  .requestMatchers("/submit-answers").permitAll().requestMatchers("/api/questions").permitAll().requestMatchers("/api/questions/**").permitAll()
	  .requestMatchers("/api").permitAll().requestMatchers("/submitAnswers").permitAll().requestMatchers("/api/submit-answers").permitAll().requestMatchers("/api/answers/**").permitAll()
	  .requestMatchers("/").permitAll().requestMatchers("/about")
	  .permitAll().requestMatchers("/team").permitAll().requestMatchers("/contact").permitAll().requestMatchers("/events").permitAll()
	  .requestMatchers("/footer").permitAll().requestMatchers("/partner").permitAll().requestMatchers("/register").permitAll().requestMatchers("home")
	    .permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login")
	    .defaultSuccessUrl("/home", true).permitAll().and().logout().invalidateHttpSession(true)
	    .clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    .logoutSuccessUrl("/login?logout").permitAll();
	 
      http.authorizeHttpRequests(
    		  
    		  auth -> auth.requestMatchers("assets/css/**", "assets/img/**", "assets/js/**", "assets/vendor/**").permitAll()
    		  
    		  );
      
      
      
 
  return http.build();

 }
 
@Bean
WebSecurityCustomizer customizeWebSecurity() {
	
	return (web) -> web.ignoring().requestMatchers("assets/css/**", "assets/img/**", "assets/js/**", "assets/vendor/**");
}
 
 
 
 
 

 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

 }
 
 
 
}