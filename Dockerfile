FROM amazon/dynamodb-local
CMD ["-jar", "DynamoDBLocal.jar", "-sharedDb", "-dbPath", ".", "-optimizeDbBeforeStartup"]

#For AWS CLI
#ENV AWS_ACCESS_KEY_ID=fake_access_key\
#    AWS_SECRET_ACCESS_KEY=fake_secret_access_key\
#    DYNAMODB_REGION=ap-northeast-1
##Create the process you want to execute from the AWS CLI with shellscript under bin
#COPY bin bin

FROM openjdk:11
WORKDIR /wainer/app-transferenciawainer
COPY /build/libs/transferenciaWainer-0.1-all.jar /app.jar
#em tempo de build
ARG PORT_BUILD=8080
#em tempo de execucao
ENV PORT=$PORT_BUILD
EXPOSE $PORT_BUILD
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]
CMD ["-jar","/app.jar"]