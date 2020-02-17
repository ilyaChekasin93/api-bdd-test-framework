Feature: example

  Scenario: example test
     Given baseUrl is 'https://reqres.in'
     And user add request header with name 'user-agent' and value 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36'
     And user add request header with name 'Content-Type' and value 'application/json'

     And user add values to pojo with name 'UsersPOSTrequestCheam':
        | $.name | morpheus |
        | $.job  | leader   |

     When user send 'POST' request to '/api/users'

     Then response code should equal to '201'
     And response body value by jsonPath '$.name' should equal to 'morpheus'
     And response body value by jsonPath '$.job' should equal to 'leader'
     And response body value by jsonPath '$.id' should exist
     And response body value by jsonPath '$.createdAt' should exist