@gend
Feature:
  To Verify and Test Genderize APIs Test Cases

  Scenario:
  To verify Predicting the gender of a single name
    Given Setting RestAssured connection for "https://api.genderize.io"
    Then User should be able to search by name as "peter"

   Scenario Outline: To Verify Classification in the scope of a specific country as "<countryName>"
     Given Setting RestAssured connection for "https://api.genderize.io"
     Then user should be able to do classification in the scope of a specific country And Name as "KIM" And "<countryName>"

     Examples:
       | countryName |
       | US   |
       | DK  |

     Scenario: To verify Checking the gender of multiple Users by name in one request
       Given Setting RestAssured connection for "https://api.genderize.io"
       Then user should be able to check genders of multiple users "peter,lois,stewie" with single request


