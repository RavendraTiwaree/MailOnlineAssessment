@Regression
Feature: Mail Online Web Page

  @UI_TC01
  Scenario: TC01-Verify the Mail Online web page
    Given User Launch the Application
    Then User verify the webpage displays current date and time
    And User navigate to the Sport menu
    Then User Verify that primary navigation colour Sport and secondary navigation Football are same
    And User click on the Football sub navigation item
    Then User click the first article displayed and traverse to the gallery image
    When User click on view gallery icon
    Then User verify the has previous and next arrows buttons
   Then User navigates to a video embedded within the article and click the full screen
    When User click on the Facebook share icon
    Then User verify it opens 'Facebook' modal dialog
    Then User get the point for 'Liverpool' team from Premier League Table section


















