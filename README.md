This is demo applicaion for producer consumer using Apache Kafka.

Steps to run app:

1)Download docker-compose file and put in a seperate folder. open cmd there. Run "docker-compose up -d" command. It will start zookeeper and kafka broker.
(ignore this step if already have kafka running locally)

2)Run springboot app.

3)You can create kafka msg using API "/v1/producer/sendMessage"

or using broker can create msg

kafka-console-producer --bootstrap-server localhost:9092 --topic demo.topic.name

4)kafka consumer wil consume it.

5)check console messages for success/fail.

6)I have created couple of tests cases also.
