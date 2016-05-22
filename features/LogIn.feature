Feature: Dobby LogIn

		As 		an user
		I want 		to log in
		so that		Dobbys control-pannel is unlogged

		
  Scenario Outline: Dobby LogOut
  
		Given 		I wait for the "Splashscreen" screen to appear
				
		When 	 		I wait for 1 seconds
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
		
		Then 			I should see "Connect Fragment"

	Examples:
		| User  		|   Password    |
		| Dobby 		|   123         |



  Scenario Outline: Dobby LogIn failed - User not found
  
		

		Given 			I wait for the "Splashscreen" screen to appear
   
		

		When 	 		I wait for 1 seconds
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"		
		

		Then 			I should see "User not found"

		

	Examples:
			
		| User 			|   Password    |
			
		| Daniel 		|   123         |
			
		| Marie			|   123         |
			
		| Martin 		|   123         |
			
		| Nico 			|   123         |



  Scenario Outline: Dobby LogIn failed - Wrong Password
   
		

		Given			I wait for the "Splashscreen" screen to appear
   
		

		When 	 		I wait for 1 seconds
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"		
		

		Then 			I should see "Wrong password"

	

	Examples:
			
		| User 			|   Password    |
			
		| Dobby 		|   4444        |
			
		| Dobby 		|   235         |
			
		| Dobby 		|   23433       |
			
		| Dobby 		|   234         |
	