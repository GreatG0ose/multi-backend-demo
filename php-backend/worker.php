<?php

/**
 * Sample GRPC PHP server.
 */

use Server\HelloService;
use Spiral\RoadRunner\GRPC\Server;
use Spiral\RoadRunner\Worker;
use Service\HelloServiceInterface;

require __DIR__ . '/vendor/autoload.php';

$server = new Server(null, [
    'debug' => false, // optional (default: false)
]);

$server->registerService(HelloServiceInterface::class, new HelloService());

$server->serve(Worker::create());