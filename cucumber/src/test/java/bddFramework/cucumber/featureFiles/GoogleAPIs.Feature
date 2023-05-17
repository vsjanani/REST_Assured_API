Feature: Google Map API HTTP request

Scenario: Validate if location is added using Google add API.
Given JSON Payload and Header Key
When User POST "maps/api/place/add/json" resource 
Then Response Status code should be 200
And "scope" in response body should be "APP"
And "status" in response body should be "OK"

Scenario Outline: Validate if location is added using Google add API using dynamic data.
Given JSON Payload and Header Key with "<name>","<address>","<language>"
When User POST "maps/api/place/add/json" resource 
Then Response Status code should be 200
And "scope" in response body should be "APP"
And "status" in response body should be "OK"

Examples:
|name|address|language|
|jishnu|hyat|tamil|
|jakki|dubai|kannada|