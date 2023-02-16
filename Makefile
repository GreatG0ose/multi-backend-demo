JAVA_API_ROOT=java-demo/api
JAVA_API_SRC=${JAVA_API_ROOT}/src/main/java/com/demo/api
GO_API_SRC=go-backend/api
PHP_API_SRC=php-backend/src/

compile: clean prepare protoc git_add

clean:
	rm -rf ${JAVA_API_SRC}/model
	rm -rf ${JAVA_API_SRC}/service
	rm -rf ${GO_API_SRC}/model
	rm -rf ${GO_API_SRC}/service
	rm -rf ${PHP_API_SRC}/Model
	rm -rf ${PHP_API_SRC}/GPBMetadata
	rm -rf ${PHP_API_SRC}/Service

prepare:
	mkdir -p ${JAVA_API_SRC}
	mkdir -p ${GO_API_SRC}
	mkdir -p ${PHP_API_SRC}

protoc: protoc_model protoc_service

protoc_model:
	PATH=${PATH}:${GOPATH}/bin \
	protoc -I=api \
		--java_out=${JAVA_API_ROOT}/src/main/java \
		--go_out=${GO_API_SRC} --go_opt=paths=source_relative \
		--php_out=${PHP_API_SRC} \
		 api/model/person.proto

protoc_service:
	PATH=${PATH}:./protobuf/plugins/grpc/macos-arm:${GOPATH}/bin \
	protoc \
		-I=api \
		--java_out=${JAVA_API_ROOT}/src/main/java \
		--grpc-java_out=${JAVA_API_ROOT}/src/main/java \
		--go_out=${GO_API_SRC} --go_opt=paths=source_relative \
		--go-grpc_out=${GO_API_SRC} --go-grpc_opt=paths=source_relative \
		--php_out=${PHP_API_SRC} \
		--php-grpc_out=${PHP_API_SRC} \
		api/service/hello.proto

git_add:
	git add .