


Feature: Dobby - LogIn

		As 			an user
		I want 		to log in into the intern main page
		so that		I can control Dobby

		
  Scenario Outline: Dobby LogIn
		
		Given 		I wait for the "MainActivity" screen to appear
   
		When 		I press "buttonLogin"
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
			
		Then 		I should see "Successfully logged in"
	
		Examples:
			| 	User		 |    Password    |
			| 	Dobby	 |    123         |

	
	
  Scenario Outline: Dobby LogIn failed - User not found
  
		Given 		I wait for the "MainActivity" screen to appear
   
		When 		I press "buttonLogin"
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"	
			
		Then 		I should see "User not found"

		Examples:
			| User 		|	Password    |
			| Daniel 	|	123         |
			| Marie		|	123         |
			| Martin 	|	123         |
			| Nico 		|	123         |



  Scenario Outline: Dobby LogIn failed - Wrong Password
   
		Given		I wait for the "MainActivity" screen to appear
   
		When 		I press "buttonLogin"
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
			
		Then 		I should see "Wrong Password"

		Examples:
			| User 		|   Password    |
			| Dobby 		|   4444        |
			| Dobby 		|   235         |
			| Dobby 		|   23433       |
			| Dobby 		|   234         |
	

