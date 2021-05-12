Feature: Categories

  Scenario Outline: Login and create new Category
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When I click on Categories
    Then page should be "<categories_page_title>"
    When I click on create button
    Then will be categories "<add_form_page_title>"
    When I provide title as "<title>" and code as "<code>"
    And I click on add button
    Then page should be "<categories_page_title>"
    When open dropdown menu
    And click logout button
    Then user logged out



    Examples:
      | username | password | name  | categories_page_title    | add_form_page_title | code      | title      |
      | admin    | admin    | admin | View category list       | Add Category Form   | test_code | test_title |