package vn.tiki.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.tiki.webflux.model.User;
import vn.tiki.webflux.model.UserEvent;

import java.time.Duration;

public class UserHandler {

    public UserHandler(){
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user, User.class);
    }

    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<User> user = Flux.just(User.builder().age(10).id("").name("Nguyen Van A").salary(1000.0d).build());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user, User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String userId = request.pathVariable("userId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = Mono.just(User.builder().age(10).id(userId).name("Nguyen Van A").salary(1000.0d).build());
        return userMono.flatMap(user -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(user)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> streamEvents(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1))
                .map(val -> UserEvent.builder().value("" + val).msg("Tiki user event").build()), UserEvent.class);
    }
}
