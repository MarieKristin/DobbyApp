Feature: Dobby OpenMenu

	As 			an user	
	I want 			to open the menu	
	so that			I can choose the menu topics

		
Scenario Outline: Dobby OpenMenu - Successfull		

	Given	 		I wait for the "Splashscreen" screen to appear		
		And 		I wait for 8 seconds
		And 		I enter "<User>" into input field number 1
		And 		I press the enter button
		And I wait for 8 seconds
		And 		I enter "<Password>" into input field number 2
		And 		I press the enter button
		And 		I press "loginConfirm"		
		And I wait for 8 seconds
		And I go back

	When 			I click on screen 3% from the left and 7% from the top 	
		And I wait for 8 seconds			
		
	Then 			I should see "About this Project"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |


Scenario Outline: Dobby OpenMenu - Failed
  	
	Given	 		I wait for the "Splashscreen" screen to appear		
		And 		I wait for 8 seconds
		And 		I enter "<User>" into input field number 1
		And 		I press the enter button
		And 		I wait for 8 seconds
		And 		I enter "<Password>" into input field number 2
		And 		I press the enter button
		And 		I press "loginConfirm"
		And I wait for 8 seconds
		And I go back		

	When 			I click on screen 50% from the left and 7% from the top 				
		And I wait for 8 seconds

	Then 			I should not see "About this Project"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |