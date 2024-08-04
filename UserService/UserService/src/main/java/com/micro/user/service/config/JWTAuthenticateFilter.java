//package com.micro.user.service.config;
//
//import com.micro.user.service.service.impl.JWTService;
//import com.micro.user.service.service.impl.UserDetailsServiceImpl;
//import com.micro.user.service.service.impl.UserServiceImpl;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Service
//public class JWTAuthenticateFilter extends OncePerRequestFilter {
//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader=   request.getHeader("Authorization");
//        if(authHeader ==null || !authHeader.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//        }
//        String jwt=  authHeader.substring(7);
//        String username=jwtService.exctractUserName(jwt);
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
//            if(userDetails!=null  && jwtService.isTokenValid(jwt)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(
//                                username, userDetails.getPassword(), userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//            filterChain.doFilter(request,response);
//        }
//    }
//}
