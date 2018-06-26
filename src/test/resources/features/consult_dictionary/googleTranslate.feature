Feature: Google Translate functionality
  I would like to be able to translate english words


  #Negative pallets path Step 1
  Scenario Outline: Google translate english
    Given user is on google translate page
    When write english word
      | english_word   | expected_result   |
      | <english_word> | <expected_result> |
    Then "<expected_result>" must displayed

    Examples: Step 1 Pallets
      | english_word | expected_result |
      | dog          | куче            |
      | cat          | котка           |
      | door         | врата           |
      | beer         | Бира            |


