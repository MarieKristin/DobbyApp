Feature: Dobby Connection		

	As 			    an user
	I want 			connect to Dobby		
	so that			I can control Dobby	
  

Scenario Outline: Connection - Successfull
		

	Given	 	I wait for the "Splashscreen" screen to appear		
	And 		I wait for 8 seconds
	And 		I enter "<User>" into input field number 1
	And 		I press the enter button
	And I wait for 8 seconds
	And 		I enter "<Password>" into input field number 2
	And 		I press the enter button
	And 		I press "loginConfirm"
	And I wait for 8 seconds
	And I go back		

	When 		I click on screen 50% from the left and 20% from the top

	Then 		I should not see "Connection Established"
	    # No Hardware available

Examples:
	| User  		|   Password    |
	| Dobby 		|   123         |


Scenario Outline: Connection - Failure


	Given	 	I wait for the "Splashscreen" screen to appear
	And 		I wait for 8 seconds
	And 		I enter "<User>" into input field number 1
	And 		I press the enter button
	And I wait for 8 seconds
	And 		I enter "<Password>" into input field number 2
	And 		I press the enter button
	And 		I press "loginConfirm"
	And I wait for 8 seconds
	And I go back

	When 		I click on screen 50% from the left and 20% from the top

	Then 		I should see "Connection Error - try again"

Examples:
	| User  		|   Password    |
	| Dobby 		|   123         |


