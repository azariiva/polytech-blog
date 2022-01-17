FROM openjdk:11-jdk
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/install/nano-blogging-service/ /app/
WORKDIR /app/bin
CMD ["./nano-blogging-service"]