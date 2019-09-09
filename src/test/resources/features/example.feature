Feature: example

  Scenario: test
     Given baseUrl is 'https://reqres.in'
     Given I set 'user-agent' header to 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36'
     Given I set 'Accept' header to 'application/json'
     And I set 'Content-Type' header to 'application/json'

     And I set values to pojo with name 'UsersPOSTrequestCheam':
        | $.name | morpheus |
        | $.job  | leader   |

     When I 'POST' resource '/api/users'
     Then response code should be '201'
     And response header 'Content-Type' should exist
     And response body should be valid json
     And response body path '$.name' should be 'morpheus'
     And response body path '$.job' should be 'leader'
     And response body path '$.id' should exists
     And response body path '$.createdAt' should exists