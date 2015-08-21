Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given following Books exist:
      | title         | author    | Date     |
      | One good book | Anonymous | 14 March 2013 |
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 23 August 2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 01 January 2012
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
    And Book 2 should have the title 'Some other book'
    And Book 1 should have the title 'One good book'

  Scenario: Search books by Author Name
    Given following Books exist:
      | title         | author    | Date     |
      | One good book | Anonymous | 14 March 2013 |
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 23 August 2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 01 January 2012
    When the customer searches for books written by 'Fred Flintstone'
    Then 1 books should have been found
    And Book 1 should have the title 'How to cook a dino'

  Scenario: Search books by Book Name
    Given following Books exist:
      | title         | author    | Date     |
      | One good book | Anonymous | 14 March 2013 |
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 23 August 2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 01 January 2012
    When the customer searches for book 'Some other book'
    Then 1 books should have been found
    And Book 1 should have the Author 'Tim Tomson'
