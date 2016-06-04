Feature: SteerLeftwards

	As 				an user	
	I want 			to drive Dobby leftwards	
	so that			I can drive circles

		
Scenario Outline: SteerLeftwards - Successfull		

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

	When 			I enter "SteerLeftwards" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should see "SteerLeftwards"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: SteerLeftwards - Failure - Wrong Code	

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

	When 			I enter "Stee" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should not see "SteerLeftwards"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: SteerLeftwards - Failure - Wrong Page

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

	

	When 	  		I press "General Information"		
		And I wait for 2 seconds	

	Then I should not see "Enter command here"	
			


Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |