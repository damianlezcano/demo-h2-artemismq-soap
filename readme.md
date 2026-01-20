java -cp /home/damian/rh/h2/bin/h2*.jar org.h2.tools.Server -webAllowOthers -tcpAllowOthers -ifNotExists

"/home/damian/rh/apache-artemis-2.44.0/test/bin/artemis" run

mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-javaagent:target/agents/elastic-apm-agent.jar \
  -Delastic.apm.service_name=mock-api \
  -Delastic.apm.api_key=azdJbHg1c0JnOHJ6R24taEZrRWk6djJ4T1l6VElIMG1BUEpnblZ3akRIUQ== \
  -Delastic.apm.server_url=https://my-observability-project-ace710.apm.us-central1.gcp.elastic.cloud:443 \
  -Delastic.apm.environment=development \
  -Delastic.apm.application_packages=com.example.mockapi \
  -Delastic.apm.log_level=INFO"