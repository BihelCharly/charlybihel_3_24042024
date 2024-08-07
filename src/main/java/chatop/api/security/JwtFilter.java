package chatop.api.security;

import chatop.api.exception.BadRequestException;
import chatop.api.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Service
public class JwtFilter extends OncePerRequestFilter {

    private UserService userService;
    private JwtService jwtService;

    private final AntPathRequestMatcher[] ignoredMatchers = new AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/auth/register", "POST"),
            new AntPathRequestMatcher("/auth/login", "POST")
    };

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        for (AntPathRequestMatcher matcher : ignoredMatchers) {
            if (matcher.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String token;
        String username = null;
        boolean isTokenExpired = true;

        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);
            // CHECK IF TOKEN IS EXPIRED
            isTokenExpired = jwtService.isTokenExpired(token);
            // IF NOT THEN EXTRACT USERNAME
            username = jwtService.extractUserName(token);
        }
        // IF TOKEN IS NOT EXPIRED + USERNAME IS NOT NULL + SECURITY IS NOT DEFINED IN CONTEXT
        if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // THEN GET USER DETAILS FROM SERVICE BY USERNAME
            UserDetails userDetails = userService.loadUserByUsername(username);
            // CREATE AUTH TOKEN FROM USER DETAILS
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
