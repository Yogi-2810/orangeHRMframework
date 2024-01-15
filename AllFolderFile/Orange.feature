Feature: Test OrangeHRM application

  Scenario: Login Page Functionality
    Given user is on login Page
    When user Added allCredentials and click on login button
    And user is on homePage and capture the homepage title

  Scenario: homePagefunctionality
    When user capture homepage url
    And user click on pimlink

  Scenario Outline: Add new employee functionality
    When user click on add employee
    When user add "<fname>","<mname>","<lname>" and click on save button
    Then user capture the employee Id
    And user clickon employee list and search the employee by using employee id
    Then user select the employyee and clickon delete
    Then validate the conform delete window and click on yes delete

    Examples: 
      | fname | mname | lname |
      | abc1  | PRQ1  | xyz1  |
      | abc2  | PRQ2  | xyz2  |
      | abc3  | PRQ3  | xyz3  |

  Scenario: validate logout functionality
    When user click on profile image
    Then click on logout
