package com.mainacad.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class CurrencyExtractionService {

    private List<String> list = Collections.synchronizedList(new ArrayList<>());
    AtomicLong extractionTime = new AtomicLong(new Date().getTime());

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void getUkrNetResponse() {
        list.add(String.valueOf(new Date().getTime()));
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
    public void writeData() {
        if (list.isEmpty()) {
            return;
        }

        List<String> elementsToRemove = new ArrayList<>();
        for (String text : list) {
            if (Long.parseLong(text) >= extractionTime.get()) {
                log.info(new Date(Long.parseLong(text)).toString());
            } else {
                elementsToRemove.add(text);
            }
        }

//        list.stream().filter(it -> Long.parseLong(it) >= extractionTime.get()).
//                forEach(it -> log.info(new Date(Long.parseLong(it)).toString()));
        list.removeAll(elementsToRemove);
        extractionTime.set(new Date().getTime());
    }

}