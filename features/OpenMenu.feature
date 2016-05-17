


Feature: Dobby OpenMenu

		As 			an user
		I want 		to open the menu
		so that		I can choose the menu topics

		
  Scenario Outline: Dobby OpenMenu
  
		Given 		I wait for the "MainActivity" screen to appear
			And 		I press "buttonLogin"
			And 		I enter "<User>" into input field number 1
			And 		I press the enter button
			And 		I enter "<Password>" into input field number 2
			And 		I press the enter button
			And 		I press "loginConfirm"
			And 		I should see "Successfully logged in"
			
		When 		I click on screen 3% from the left and 7% from the top 	
			
		Then 		I should see "About this Project"

	Examples:
		| User  		|   Password    |
		| Dobby 		|   123         |