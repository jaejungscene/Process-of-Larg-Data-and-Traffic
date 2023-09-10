package jaejung.springprac.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().requestMatchers("/user/**");
//    }

//    @Bean
//    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/user/**").authenticated()
//                .requestMatchers("/manager/**").hasAnyRole("admin", "manager")
//                .requestMatchers("/admin/**").hasRole("admin")
//                .anyRequest().permitAll()
//            .and()
//                .formLogin()
//                .loginPage("/loginForm")
//            .and()
//                .csrf().disable();
//
//        return http.build();
//    }
}
