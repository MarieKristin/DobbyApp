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
	And I wait for 8 seconds
	And 		I press "loginConfirm"
	And I wait for 8 seconds
	And I go back		

	When 		I click on screen 50% from the left and 16% from the top
	And I wait for 8 seconds

	Then 		I should see "Connection Error - try again"

Examples:
	| User  		|   Password    |
	| Dobby 		|   123         |


