Feature: Dobby LogOut

		

	As 			an user
		
	I want 			to log out
		
	so that			Dobbys control-pannel is logged

		
  


Scenario Outline: Dobby LogOut
  
		

	Given	 		I wait for the "Splashscreen" screen to appear
				
	When 	 		I wait for 1 seconds
		And I wait for 10 seconds
		And 		I enter "<User>" into input field number 1
		And I wait for 10 seconds
		And 		I press the enter button
		And I wait for 10 seconds
		And 		I enter "<Password>" into input field number 2
		And I wait for 10 seconds
		And 		I press the enter button
		And I wait for 10 seconds
		And 		I press "loginConfirm"

		And I wait for 10 seconds
		And I go back		

	When 			I click on screen 3% from the left and 7% from the top 	
		And I wait for 10 seconds
		And 		I press "Logout"			
		

	Then 			I should see "Successfully logged out"

	

Examples:

	| User  		|   Password    |
		
	| Dobby 		|   123         |



Scenario Outline: Dobby LogOut
  
		

	Given	 		I wait for the "Splashscreen" screen to appear
				
	When 	 		I wait for 10 seconds
		And 		I enter "<User>" into input field number 1
		And I wait for 10 seconds
		And 		I press the enter button
		And I wait for 10 seconds
		And 		I enter "<Password>" into input field number 2
		And I wait for 10 seconds
		And 		I press the enter button
		And I wait for 10 seconds
		And 		I press "loginConfirm"
		And I wait for 10 seconds
		And I go back		

	When 			I click on screen 3% from the left and 7% from the top 
		And I wait for 10 seconds	
		And 		I press "Connect"	
			
	Then 			I should not see "Successfully logged out"

	

Examples:

	| User  		|   Password    |
		
	| Dobby 		|   123         |