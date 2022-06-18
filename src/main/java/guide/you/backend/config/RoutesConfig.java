package guide.you.backend.config;

import guide.you.backend.rest.PostController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(PostController postController) {
        return route(GET("/posts"), postController::all)
                .andRoute(POST("/posts"), postController::create)
                .andRoute(GET("/posts/{id}"), postController::get)
                .andRoute(PUT("/posts/{id}"), postController::update)
                .andRoute(DELETE("/posts/{id}"), postController::delete);
    }
}
