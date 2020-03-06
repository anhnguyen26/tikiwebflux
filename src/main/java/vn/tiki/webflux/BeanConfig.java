package vn.tiki.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import vn.tiki.webflux.controller.UserHandler;

@Configuration
public class BeanConfig {

    @Bean
    public RouterFunction<ServerResponse> route() {
        UserHandler userHandler = new UserHandler();
        return RouterFunctions
                .route(RequestPredicates.POST("/users").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), userHandler::createUser)
                .andRoute(RequestPredicates.GET("/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::listUser)
                .andRoute(RequestPredicates.GET("/users/{userId}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getUserById)
                .andRoute(RequestPredicates.PUT("/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::createUser)
                .andRoute(RequestPredicates.DELETE("/users/{userId}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser)
                .andRoute(RequestPredicates.GET("/users/events/stream").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::streamEvents);
    }

}
