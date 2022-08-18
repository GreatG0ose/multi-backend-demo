package com.demo;


import com.demo.api.model.PersonOuterClass;
import com.demo.api.service.HelloServiceGrpc;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var targets = List.of("localhost:8080", "localhost:8081");

        targets.forEach(target -> {
            var channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
            try {
                var client = HelloServiceGrpc.newBlockingStub(channel);
                var response = client.greet(
                        PersonOuterClass.Person.newBuilder()
                                .setFirstName("John")
                                .setLastName("Doe")
                                .build()
                );
                System.out.println(response);
            } finally {
                try {
                    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
