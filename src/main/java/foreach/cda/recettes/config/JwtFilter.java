package foreach.cda.recettes.config;


import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            final String header = request.getHeader("Authorization");

            String username = null;
            String token = null;
            System.out.println(header);
            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
                if (JwtUtil.validateToken(token)) {
                    username = JwtUtil.extractUsername(token);
                }
            }

            if (username != null && JwtUtil.validateToken(token)) {
                // récupérer les informations supplémentaires
                Integer userId = JwtUtil.extractUserId(token);
                String roleClaim = JwtUtil.extractRole(token);
                String grantedRole = "ROLE_USER";
                if ("ADMIN".equalsIgnoreCase(roleClaim)) {
                    grantedRole = "ROLE_ADMIN";
                }
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null,
                                List.of(new SimpleGrantedAuthority(grantedRole)));
                auth.setDetails(userId);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            
        }
    }

}