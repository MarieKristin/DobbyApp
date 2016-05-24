Feature: Dobby - Get General Information Page

	As 				an user
	I want 				to get general information 
	so that				I can be informed about the developers, software version,...
	

 Scenario Outline: Navigate to "Get General Information Page"
    
	Given 			I wait for the "Splashscreen" screen to appear	
		And 		I wait for 1 seconds
		And 		I enter "<User>" into input field number 1
		And 		I press the enter button
		And 		I enter "<Password>" into input field number 2
		And 		I press the enter button
		And 		I press "loginConfirm"
		And I wait for 5 seconds


	When 			I click on screen 3% from the left and 7% from the top
	    And I wait for 5 seconds
		And 		I press "General Information"	
			
	Then 			I should see "General Information"
	
Examples:
| User		|   Password    |
| Dobby 	|	123	|


 Scenario Outline: Check version on "General Information Page"
    
	
	Given 			I wait for the "Splashscreen" screen to appear	
		And 		I wait for 1 seconds
		And 		I enter "<User>" into input field number 1
		And 		I press the enter button
		And 		I enter "<Password>" into input field number 2
		And 		I press the enter button
		And 		I press "loginConfirm"
		And I wait for 5 seconds
	

	When 			I click on screen 3% from the left and 7% from the top
	    And I wait for 5 seconds
		And 		I press "General Information"	
			
	Then 			I should see "v0.1"
	
Examples:	
| User		|   Password    |	
| Dobby 	|	123	|


 

 Scenario Outline: Check developer on "General Information Page"
    
	Given 			I wait for the "Splashscreen" screen to appear	
		And 		I wait for 1 seconds
		And 		I enter "<User>" into input field number 1
		And 		I press the enter button
		And 		I enter "<Password>" into input field number 2
		And 		I press the enter button
		And 		I press "loginConfirm"
		And I wait for 5 seconds
		And I go back

	When 			I click on screen 3% from the left and 7% from the top
	 	And I wait for 5 seconds
		And 		I press "General Information"	
			
	Then 			I should see "Daniel Stumpf, Nicolas Huentz, Marie Kaiser and Martin M"	

Examples:	
| User		|   Password    |	
| Dobby 	|	123	|


 
















