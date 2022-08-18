package com.demo;

import com.demo.api.impl.*;

public class Main {
    public static void main(String[] args) throws Exception {
        var server = new JavaBackendServer(8080);
        server.start();
        server.blockUntilShutdown();
    }
}
