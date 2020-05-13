Java
- Version 13 (https://docs.oracle.com/en/java/javase/13/)
- Spring boot (https://spring.io/projects/spring-boot)
- Spring data jpa (h2 database) (https://spring.io/projects/spring-data-jpa)
- Web flux reactive no blocking (https://spring.io/guides/gs/reactive-rest-service/)
- Logs para Logstash json format (https://github.com/logstash/logstash-logback-encoder)
- Swagger usando spring apidocs (https://springdoc.org/)
- Lombok (https://projectlombok.org/)
- Spring validator 
- Metricas (https://docs.spring.io/spring-metrics/docs/current/public/prometheus)
- Health check (valida as dependencias)
- Testes unitatios Cucumber/junit/mockito
- Flyway Migration
- Docker

TODO:
x - netdata 
x - gitlab (ansible/ ec2 e ecs) notification(deploy)
x - prometheus/grafana (alerts)
x - newrelic
x - ELK 


prometheus (toolkit de monitoramento e alerta de sistema) captura
grafana (plataforma de observacao de dados) mostra

mvn test
mvn verify -DskipTest
mvn clean compile -P development flyway:migrate
mvn spring-boot:run