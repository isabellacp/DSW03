package br.ufscar.trabalho.config;


import br.ufscar.trabalho.security.UsuarioDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
// Controladores REST
                .antMatchers("/clientes", "/agencias", "/pacotes").permitAll()
                .antMatchers("/clientes/{\\d+}", "/agencias/{\\d+}").permitAll()
                .antMatchers("/pacotes/agencias/{\\d+}").permitAll()
                .antMatchers("/pacotes/clientes/{\\d+}").permitAll()
                .antMatchers("/pacotes/destinos/{\\w+}").permitAll()
                .antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**").permitAll()
                .antMatchers("/pacotes/listar").permitAll()
                .antMatchers("/pacotes/cadastrar").hasRole("Agencia")
                .antMatchers("/pacotes/comprar").hasRole("CLIENTE")
                .antMatchers("/pacotes/editar").hasRole("Agencia")

                .antMatchers("/agencias/listar").authenticated()
                .antMatchers("/clientes/listar").authenticated()

                .antMatchers("/agencias/cadastrar").hasRole("ADMIN")
                .antMatchers("/agencias/editar").hasRole("ADMIN")
                .antMatchers("/agencias/excluir").hasRole("ADMIN")
                .antMatchers("/clientes/cadastrar").hasRole("ADMIN")
                .antMatchers("/clientes/editar").hasRole("ADMIN")
                .antMatchers("/clientes/excluir").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}