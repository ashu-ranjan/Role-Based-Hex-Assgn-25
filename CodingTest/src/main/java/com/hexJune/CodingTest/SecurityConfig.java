package com.hexJune.CodingTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // .csrf((csrf)-> csrf.disable()) <-- using lambda expression
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/user/signUp").permitAll()
                        .requestMatchers("/api/user/token").authenticated()
                        .requestMatchers("/api/user/details").authenticated()

                        .requestMatchers("/api/patients/add").permitAll()
                        .requestMatchers("/api/make/appointment").permitAll()
                        .requestMatchers("/api/get-all/patients/{doctorId}").hasAuthority("DOCTOR")
                        .requestMatchers("/api/get/history/{patientId}").hasAuthority("DOCTOR")
                        .requestMatchers("/api/doctor/add").permitAll()
                        .requestMatchers("/api/get/patient/bySpeciality").permitAll()


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // Bean saves this object in spring's context
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}
