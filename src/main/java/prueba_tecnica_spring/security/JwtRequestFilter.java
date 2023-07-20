package prueba_tecnica_spring.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <b>Author:</b> Velfin Velasquez <br>
 * <b>Description:</b>  JwtRequestFilter<br>
 */
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtilService jwtUtilService;
    @Autowired
    public  JwtRequestFilter(UserDetailsService userDetailsService,JwtUtilService jwtUtilService){
        this.userDetailsService=userDetailsService;
        this.jwtUtilService=jwtUtilService;
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param chain FilterChain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        Map<String, Object> _response = new HashMap<>();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtilService.extractUsername(jwt);
        }

        try {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                System.out.println("aqui imprimi los autority " + userDetails.getAuthorities());
                if (jwtUtilService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    log.info("aqui imprimi los autority token " + authenticationToken);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch(Exception e){
            _response.put("code", "500");
            _response.put("message", e.getLocalizedMessage());
            log.error("ERROR EN EL PROCESO JWT: {}", e.getLocalizedMessage());
        }
        chain.doFilter(request, response);
    }



}