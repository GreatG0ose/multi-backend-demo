package impl

import (
	"context"
	"fmt"
	"go-backend/api/model"
	"go-backend/api/service"
)

type helloServiceImpl struct {
	service.UnimplementedHelloServiceServer
}

func NewHelloServer() service.HelloServiceServer {
	return &helloServiceImpl{}
}

func (h helloServiceImpl) Greet(ctx context.Context, person *model.Person) (*service.GreetResponse, error) {
	return &service.GreetResponse{
		Response: fmt.Sprintf("Hello, %s %s! From go-backend", person.GetFirstName(), person.GetLastName()),
	}, nil
}
