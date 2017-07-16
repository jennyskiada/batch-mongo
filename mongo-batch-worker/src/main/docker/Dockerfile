#
# 1. clean package docker:build
# 2. docker run -p 9001:9001 --name=worker mongo-batch-worker
# 3. http://192.168.99.100:9001
#
FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 9000

# Install Spring Boot Artifact
VOLUME /tmp
ADD mongo-batch-worker-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]