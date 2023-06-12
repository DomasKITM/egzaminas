package lt.kitm.neimdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(
                                        "/css/*",
                                        "/js/*",
                                        "/registracija",
                                        "/error",
                                        "/error/*"
                                ).permitAll()
                                .requestMatchers(
                                        "/pagrindinis",
                                        "/kategorijos",
                                        "/kategorija/*",
                                        "/filmai",
                                        "/filmai/filmas/*",
                                        "/filmai/paieska",
                                        "/virseliai/*",
                                        "/komentaras/prideti"
                                ).hasAnyRole("ADMINISTRATORIUS", "SKAITYTOJAS")
                                .requestMatchers(
                                        "/kategorijos/prideti",
                                        "/kategorijos/redaguoti/*",
                                        "/kategorijos/redaguoti",
                                        "/kategorijos/trinti",
                                        "/filmai/prideti",
                                        "/filmai/redaguoti/*",
                                        "/filmai/redaguoti",
                                        "/filmai/trinti",
                                        "/filmai/trinti/virseli",
                                        "/vartotojai",
                                        "/vartotojas/uzblokuoti",
                                        "/vartotojas/atblokuoti",
                                        "/vartotojas/*"
                                ).hasRole("ADMINISTRATORIUS")
                ).formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/")
                        .defaultSuccessUrl("/pagrindinis")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }
}
