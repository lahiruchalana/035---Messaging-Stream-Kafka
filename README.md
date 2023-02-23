## Message Streaming Application
### Built using Kafka

This is a spring boot application.
Integrated with MySQL, Firebase, Hibernate, Spring Data JPA and Kafka.


First phase - Messaging Streaming
Second phase - Location Streaming
Third phase - User Data Streaming

## How to start...

First go to the Kafka Folder path using CMD.
cd <copied file path>

### Start Zookeeper
Type below command in CMD in Kafka Folder path.
* .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

### Start Kafka Broker Service
Type below command in another CMD in Kafka Folder path.
* .\bin\windows\kafka-server-start.bat .\config\server.properties

### To Read Kafka Streams
Type below command in another CMD in Kafka Folder path.
* .\bin\windows\kafka-console-consumer.sh --topic <type-here-the-topic-name> --from-beginning --bootstrap-server localhost:9092