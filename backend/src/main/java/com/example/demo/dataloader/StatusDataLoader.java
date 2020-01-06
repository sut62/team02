package com.example.demo.dataloader;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepo;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StatusDataLoader implements ApplicationRunner {

    @Autowired private StatusRepo statusRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("โสด","แต่งงานแล้ว","อย่าร้าง").forEach(snapshot -> {
            Status status = new Status();
            status.setStatus(snapshot);
            statusRepo.save(status);
        });
       
    }

    
}