package com.gate.way.filter;

import com.gate.way.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static  final Logger LOGGER= LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JWTUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            if(routeValidator.isSecured.test(exchange.getRequest())){

                LOGGER.info(" router validator{} checking about exchage varible{}"+this.getClass(),exchange.toString());

                System.out.println(exchange.toString());

                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                 throw new RuntimeException("Missing autherization header...!!!");
                }
               String authHeader= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                 authHeader=   authHeader.substring(7);

                }
                try{
//                    restTemplate.getForEntity("http://AUTH-SERVICE/validate?token="+authHeader,String.class);
                    jwtUtils.validateToken(authHeader);
                    //newly added
                  LOGGER.info("  exchage varible{ }"+this.getClass(),exchange.toString());
//                    this.populateRequestWithHeaders(exchange, authHeader);


                }catch (Exception exception){
                    System.out.println("Access invalid...!!!");
                    throw new RuntimeException("Unauthorized exception getting here ...!!!");
                }

            }

            LOGGER.info(" before filter -->  exchage varible{ }"+this.getClass(),exchange.toString());

            return chain.filter(exchange);
        }));
    }
    public static class Config{
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtils.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("roles", String.valueOf(claims.get("roles")))
                .header("tenantId", String.valueOf(claims.get("tenantId")))
                .build();
    }


}
