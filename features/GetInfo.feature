


Feature: Dobby - Get General Information Page

		As 			an user
		I want 		to get general information 
		so that		I can be informed about the developers, software version,...

		
		
  Scenario Outline: Navigate to "Get General Information Page"
    
	Given 			I wait for the "MainActivity" screen to appear
		And 			I press "buttonLogin"
		And 			I enter "<User>" into input field number 1
		And 			I press the enter button
		And 			I enter "<Password>" into input field number 2
		And 			I press the enter button
		And 			I press "loginConfirm"
		And 			I should see "Successfully logged in"
		
	When 			I click on screen 3% from the left and 7% from the top 	
		And 			I press "General Information"	
			
	Then 			I should see "Dobby-App"
	
	
	Examples:
	| User	|   Password    	|
	| Dobby 	|	123			|


 