package com.my.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("mono")
    public Mono<String> hello(String word) {
        log.info("word={}, threadName={}", word, Thread.currentThread().getName());
        Mono<String> result = Mono.fromSupplier(() -> sleep1(3L));
        return result;
    }

    @GetMapping("/mvc")
    public String mvc(String test) {
        log.info("test={}", test);
        String result = sleep1(3L);
        return result;
    }

    private String sleep1(Long id) {
        log.info("sleep1:{} start", id);
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sleep1:{} end", id);
        log.info("threadName={}", Thread.currentThread().getName());
        return String.valueOf(id);
    }

    private Mono<Integer> sleep2(Long id) {
        log.info("sleep2:{} start", id);
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sleep2:{} end", id);
        return Mono.just(id.intValue());
    }
}
