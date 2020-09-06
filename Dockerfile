FROM openjdk:11.0.7-slim
ADD target/shop.jar /
CMD java -jar -Dspring.profiles.active=$ACTIVE_PROFILE shop.jar
