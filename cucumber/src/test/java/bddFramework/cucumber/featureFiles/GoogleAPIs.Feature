Feature: Google Map API HTTP request

Scenario: Validate if location is added using Google add API.
Given "ADDPAYLOAD" and Header Key
When User hits "ADDAPI" resource using http method "POST"
Then Response Status code should be 200
And "scope" in response body should be "APP"
And "status" in response body should be "OK"

#Scenario Outline: Validate if location is added using Google add API using dynamic data.
#Given JSON Payload and Header Key with "<name>","<address>","<language>"
#When User hits "ADDAPI" resource using http method "POST" 
#Then Response Status code should be 200
#And "scope" in response body should be "APP"
#And "status" in response body should be "OK"
#And on hitting "GETAPI" using http method "GET", already created "place_id" should carry "<name>" in "name" field
#
#Examples:
#|name|address|language|
#|jishnu|hyat|tamil|
#|jakki|dubai|kannada|

#Scenario: Validate if a location is deleted using Google delete API with dynamic data.
#Given "DELETE" Payload and Header Key
#When User hits "DELETEAPI" resource using http method "DELETE"
#Then "status" in response body should be "OK"
#And on hitting "GETAPI" using http method "GET", already created "place_id" should carry "Get operation failed, looks like place_id  doesn't exists" in "msg" field