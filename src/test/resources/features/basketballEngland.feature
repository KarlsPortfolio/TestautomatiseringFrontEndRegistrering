Feature:Test of Basketball England Register Page
  Testscenario 1: create user when correct inputs in all the fields
  Testscenario 2: create user when last name is empty
  Testscenario 3: create user when password and confirmation password don't match
  Testscenario 4: create user when terms and condition box is not checked


  Scenario Outline: Register valid standard user
    Given I am using "<browser>" as a browser
    And I am at page "<page>"
    When I register a new user "<userType>"
    Then I get confirmation message "<message>"

    Examples:
      |browser|page|userType|message|
      |chrome |register|Standard User|THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND|
      |edge|register|Standard User|THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND|
      |firefox|register|Standard User|THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND|


  Scenario Outline: Validate last name field
    Given I am using "<browser>" as a browser
    And I am at page "<page>"
    When I register with last name "<lastName>"
    Then I get error message "<error>"


    Examples:
      |browser|page|lastName|error|
      |chrome |register|[empty]|Last Name is required|
      |edge|register|[empty]|Last Name is required|
      |firefox|register|[empty]|Last Name is required|


  Scenario Outline: Validate password matching
    Given I am using "<browser>" as a browser
    And I am at page "<page>"
    When I register with password "<pass>" and confirm "<confirm>"
    Then I get error message "<error>"


    Examples:
      |browser|page|pass|confirm|error|
      |chrome |register|secret_pass|pass_mismatch|Password did not match|
      |edge |register|secret_pass|pass_mismatch|Password did not match|
      |firefox |register|secret_pass|pass_mismatch|Password did not match|


    Scenario Outline: Validate terms and conditions acceptance
      Given I am using "<browser>" as a browser
      And I am at page "<page>"
      When I register with terms accepted "<status>"
      Then I get error message "<error>"

      Examples:
        |browser|page|status|error|
        |chrome |register|false|You must confirm that you have read and accepted our Terms and Conditions|
        |edge |register|false|You must confirm that you have read and accepted our Terms and Conditions|
        |firefox |register|false|You must confirm that you have read and accepted our Terms and Conditions|





