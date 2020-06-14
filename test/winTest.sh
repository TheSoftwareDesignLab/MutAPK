cd ..
mvn clean; mvn package; rm .\test\MutAPK-2.0.0.jar; cp .\target\MutAPK-2.0.0.jar .\test\
cd test
java -jar .\MutAPK-2.0.0.jar .\parameters.json | tee result.md