package main

import (
	"fmt"
	"go-backend/api/impl"
	"go-backend/api/service"
	"google.golang.org/grpc"
	"log"
	"net"
)

func main() {
	const port = 8081
	lis, err := net.Listen("tcp", fmt.Sprintf("localhost:%d", port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	log.Printf("listening on localhost:%d", port)

	var opts []grpc.ServerOption
	grpcServer := grpc.NewServer(opts...)
	service.RegisterHelloServiceServer(grpcServer, impl.NewHelloServer())

	err = grpcServer.Serve(lis)
	if err != nil {
		panic(err)
	}
}
