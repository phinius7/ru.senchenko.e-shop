Feature: Login

  Scenario Outline: Successful login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | name  |
      | admin    | admin    | admin |