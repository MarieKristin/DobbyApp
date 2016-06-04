Feature: Break

	As 				an user	
	I want 			to decelerate Dobby	
	so that			I break the accellerate-signal 

		
Scenario Outline: Break - Successfull		

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
		And I click on screen 50% from the left and 20% from the top
		And I wait for 2 seconds

	When 			I enter "Break" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should see "Break"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: Break - Failure - Wrong Code	

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
		And I click on screen 50% from the left and 20% from the top
		And I wait for 2 seconds

	When 			I enter "Br" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should not see "Break"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: Break - Failure - Wrong Page

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
		And I click on screen 3% from the left and 7% from the top 	
		And I wait for 8 seconds
		And 		I press "General Information"
	

	When 	  		I press the enter button		
		And I wait for 2 seconds			
			


Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |