@ManageReviews
Feature: Manageing KPI and Reviews

  Background: Admin is logging in to the WebApplication
    Given Admin opens "chrome" and enters url
    When he enters username and password
    Then he logged in to the application

  @MenuCheck
  Scenario: Validate all menu options available
    When admin hovers over performance menu
    Then all sub menus should be visible

  @SearchKPI @KPI
  Scenario: Admin searching KPI
    When admin navigates to kpi page to search
    And admin search KPI with job title
    Then KPI should be listed with selected job title

  @AddKPI @KPI
  Scenario Outline: Admin adding KPI
    When admin naviageta to kpi page to add
    And he enters job title as "<job_title>" and KPI as "<kpi>"
    Then KPI added to the list

    Examples: 
      | job_title    | kpi               |
      | IT Manager   | Driven to Deliver |
      | HR Associate | Tests Executed    |

  @AddReview @Review
  Scenario: Admin reviews an employee with valid inputs
    When admin navigates to manage review page to add
    And he activates the review
    Then review should be activated

  @EmployeeEvaluation @Review
  Scenario: Admin evaluates employee
    When admin navigates to manage review page to evaluate
    Then he evaluates employee and review status update as Approved

  @SearchReviewWithEmployeeName @Review
  Scenario Outline: Admin searches review by entering employee name
    When admin navigates to manage review page to search review
    And he searches for an employee review
    Then searched employee reviews should be listed

  @DeleteReview @Review
  Scenario: Admin deletes the review
    When admin navigates to manage review page to delete review
    And admin selects review clicks on delete button
    Then Review should be deleted.

  @AddValidTracker @Tracker
  Scenario Outline: Admin adds tracker
    When admin navigates to tracker page to add tracker
    And he enters tracker as "<tracker>" employee as "<ename>" reviewer as "<reviewer>"
    Then tracker added to the list

    Examples: 
      | tracker         | ename            | reviewer     |
      | 2020 Attendance | Jacqueline White | David Morris |
      | Project Grades  | Aaliyah Haq      | Luke Wright  |

  @AddInvalidTracker @Tracker
  Scenario: Configuring tracker with invalid data
    When admin navigates to tracker page to add invalid tracker
    And he try to add tracker with empty fields
    Then tracker form fields displyed error message

  @DeleteTracker @Tracker
  Scenario: Deleting Tracker
    When admin navigates to tracker page to delete tracker
    And selects tracker to delete and click delete button
    Then tracker should be deleted

  @AddTrackerLog
  Scenario: Adding Tracker Log
    When admin navigates to tracker list page
    And clicks on employee name to add log
    And admin adds the log as following
      | Quality of Work  | Positive | Good            |
      | Review Propgress | Positive | 60% Improvement |
    Then log is added in employee log list
