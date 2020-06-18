FROM openjdk:14-jdk-alpine
LABEL developers="Renato Moitinho <devs.dev@gmail.com>"
ARG profile

# JVM MEMORY (https://docs.oracle.com/en/java/javase/14/vm/java-hotspot-virtual-machine-performance-enhancements.html)
# -Xms512m              => initial java heap size
# -Xmx1024m             => maximum java heap size

#  GARBAGE COLLECTOR (https://docs.oracle.com/en/java/javase/14/gctuning/introduction-garbage-collection-tuning.html)
# -XX:+UseSerialGC      => The serial collector uses a single thread to perform all garbage collection work
# -XX:+UseParallelGC    => he parallel collector (also known as the throughput collector) performs minor collections in parallel

#  LOCALE
# -Duser.language=pt
# -Duser.region=BR
# -Dfile.encoding=UTF-8

# SPRING CONFIGURATION
# -Dspring.profiles.active=default
# -Dmanagement.metrics.export.prometheus.pushgateway.base-url=host

ENV JAVA_OPTS_DEF "-Dspring.profiles.active=$profile -Xms256m -Xmx512m -XX:+UseSerialGC -Dfile.encoding=UTF-8 -Duser.language=pt -Duser.region=BR"
WORKDIR /opt
COPY build/*.jar application.jar
COPY *.db startupdb.mv.db

EXPOSE 8080

CMD java $JAVA_OPTS $JAVA_OPTS_DEF -jar application.jar
