Feature: Dobby LogOut

		As 			an user
		I want 		to log out
		so that		Dobbys control-pannel is logged

		
  Scenario Outline: Dobby LogOut
  
		Given 		I wait for the "Splashscreen" screen to appear
				
		When 	 		I wait for 10 seconds
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
				
		Then 		I should see "Connect Fragment"

	Examples:
		| User  		|   Password    |
		| Dobby 		|   123         |