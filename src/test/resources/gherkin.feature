# primary keyword
Feature: Symphony Cucumber Meet-Up! #high level description
  I can add additional description here, ignored by Cucumber BUT visible in reports
# Matters not
  Background: I will execute before every single Scenario
  I may also be multiple line providing detailed description of Background action
    Given Symphony portal is up and running
    # Where you
    And Title is "Home | Symphony"

  # put
  @tag1
  Scenario: Check menu elements on Home Page
  I can add additional description here, ignored by Cucumber BUT visible in reports
    When We have 4 Navigation elements displayed
    When We have 4.2 Navigation elements displayed
    Then All elements are enabled
    But All change upon focus
    And I have 1 regex expression here
    And I have 2 regex expressions here
    And I have (escaping) done {here}

  Scenario: Another way to check the list of elements in one Scenario
    Then Navigation elements are all in place:
    # secondary keyword - Data Tables
      | TECHNOLOGY |
      | THAT       |
      # comments
      | MOVES      |
      | PEOPLE     |

  @tag2
  Scenario: Another way to check the list of elements in one Scenario
    Given I have something very long but very important to say:
    # secondary keyword - Doc Strings
      """
      All of this
      text needed
      somewhere to be
      """

  Scenario Outline: Run separate test for each element
    Then I will check "<element>" element
    Examples:
      | element    |
      | TECHNOLOGY |
      | THAT       |
      | MOVES      |
      | PEOPLE     |

#  @tag3
#  Scenario Template: Run separate test for each element as would Scenario Outline
#    Then I will check "<element>" element
#    Examples:
#      | element    |
#      | TECHNOLOGY |
#      | THAT       |
#      | MOVES      |
#      | PEOPLE     |