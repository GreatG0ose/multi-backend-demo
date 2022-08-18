package com.demo.api.impl;

import com.demo.api.model.PersonOuterClass;
import com.demo.api.service.Hello;
import com.demo.api.service.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void greet(PersonOuterClass.Person request, StreamObserver<Hello.GreetResponse> responseObserver) {
        Hello.GreetResponse response = Hello.GreetResponse
                .newBuilder()
                .setResponse(
                        String.format(
                                "Hello, %s %s! From Java-backend",
                                request.getFirstName(),
                                request.getLastName()
                        )
                )
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
