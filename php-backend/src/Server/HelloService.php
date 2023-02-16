<?php

namespace Server;

use Spiral\RoadRunner\GRPC;
use Service\GreetResponse;
use Service\HelloServiceInterface;

class HelloService implements HelloServiceInterface
{
    public function Greet(GRPC\ContextInterface $ctx, \Model\Person $in): GreetResponse
    {
        $response = new GreetResponse();
        return $response->setResponse("Hello from PHP");
    }
}