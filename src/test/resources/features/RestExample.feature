Feature: RestExample

  Scenario: Rest Example scenario
     Given baseUrl is 'http://petstore.swagger.io/v2'

     And user add request headers:
       | Content-Type | application/json |
       | user-agent   | Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36 |

     And user add values to pojo with name 'Order':
        | $.id        | 0                        |
        | $.petId     | 0                        |
        | $.quantity  | 0                        |
        | $.shipDate  | 2020-03-01T12:38:12.243Z |
        | $.status    | placed                   |
        | $.complete  | true                     |

     When user send 'POST' request to '/store/order'

     Then response code should equal to '200'
     And response body value by jsonPath '$.id' should equal to '0'
     And response body value by jsonPath '$.petId' should equal to '0'
     And response body value by jsonPath '$.quantity' should equal to '0'
     And response body value by jsonPath '$.shipDate' should exist
     And response body value by jsonPath '$.status' should equal to 'placed'
     And response body value by jsonPath '$.complete' should equal to 'true'