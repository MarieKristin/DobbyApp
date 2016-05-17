


Feature: Dobby Support-Page

		As 			an user
		I want 		to open the Support-Page
		so that		I can ask developers in order to solve problems with Dobby

		
  Scenario Outline: Dobby Open Support-Page
  
		Given 		I wait for the "MainActivity" screen to appear
			And 		I press "buttonLogin"
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
			And 		I should see "Successfully logged in"
			
		When 		I click on screen 3% from the left and 7% from the top
			And 		I press "Support"
			
		Then 		I should see "Send a Support-Mail"

	Examples:
		| User  		|   Password    |
		| Dobby 		|   123         |

