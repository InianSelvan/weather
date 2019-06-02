# Weather App

## Start the app
Download the jar file from [releases](https://github.com/InianSelvan/weather/releases)

open terminal and run the following command, use putty for windows
```sh
export API_ID=<your_app_id>
java -jar weather-v1.0.jar -DAPI_ID=${API_ID}
```
To get the api_id login to https://openweathermap.org/, signup and click on api key tab copy the default key or any generated key. Use that key for the above command.

## verify using maven
To run mvn verify we need to pass the argument
```sh
mvn clean  -DAPI_ID=<api_id> verify
```

## TODO 
* Add more unit tests negative scenarios
