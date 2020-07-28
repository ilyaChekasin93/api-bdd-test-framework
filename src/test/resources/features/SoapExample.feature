Feature: SoapExample

  Scenario: Soap Example scenario

     Given user set web service URL 'http://www.dneonline.com/calculator.asmx'
     And user set SOAP action 'http://tempuri.org/Add'
     And user add values to SOAP pojo with name 'Add':
          | intA | 2 |
          | intB | 2 |

     When user execute SOAP request

     Then SOAP response body value by xPath 'addResult' should be '4'