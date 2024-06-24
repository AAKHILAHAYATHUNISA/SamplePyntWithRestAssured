@all @api
Feature: API Automation - JSON & ObjectMapper

@apijson
Scenario: API automation using json 
Given User gets the api response
When the list is not empty
Then extracts ids
And extracts tags 


#@apiobjectmapper
#Scenario: API automation using objectmapper
#Given User gets the api response
#When the list is not empty
#Then extracts ids
#And extracts tags 



