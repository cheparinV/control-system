package org.demo.project.controlsystem.api;

import org.demo.project.controlsystem.service.PassService;
import org.demo.project.controlsystem.service.impl.PassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Api для запросов пропусков
 */
@RestController
public class PassController {

    private final PassService passService;

    @Autowired
    public PassController(PassServiceImpl passService) {
        this.passService = passService;
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Mono<ResponseEntity<Object>> check(@RequestParam("roomId") Long roomId,
                                              @RequestParam("entrance") Boolean entrance,
                                              @RequestParam("keyId") Long keyId) {
        final Mono<ResponseEntity<Object>> responseEntityMono = Mono.just(passService.passUser(keyId, roomId, entrance))
                .filter(re -> re)
                .map(re -> ResponseEntity.ok().build())
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).build()));

        return responseEntityMono;
    }




}
