Feature: Sensor

	As 				an user	
	I want 			activate the sensor	
	so that			Dobby is not driving against objects

		
Scenario Outline: Sensor - Successfull		

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

	When 			I enter "Sensor" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should see "Sensor"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: Sensor - Failure	- Wrong Code	

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

	When 			I enter "s" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should not see "Sensor"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |



Scenario Outline: Sensor - Failure	- Wrong Answer

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

	When 			I enter "Sensor" into input field number 1	
		And 		I press the enter button		
		And I wait for 2 seconds			
			
	Then 			I should not see "Cheers"

Examples:	
	| User  		|   Password    |	
	| Dobby 		|   123         |