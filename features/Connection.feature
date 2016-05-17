


Feature: Connection feature

		As 			an user
		I want 		to connect Dobby with my mobile phone
		so that		I can control dobby
		
		


	Scenario: 		Connection is successfull 
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connect button
		And			the WIFI-Status is online
		And			the system sets Counter to 1
		And			the system finds Dobby
		And 			the system sends validation key
		And 			Dobby verifies the validation key
		
		Then			the user will see a message "Connection established"
		

		
		
	Scenario: 		The system turns on WIFI and connect successfullly
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connection button
		And			the WIFI-Status is offline	
		
		Then			the system turns on WIFI
		And			the system sets Counter to 1
		And			the system finds Dobby
		And 			the system sends validation key
		And 			Dobby verifies the validation key
		And			the user will see a message "Connection established"	
	
	
	
	
	Scenario: 		The system can not find Dobby and increases the counter
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connection button
		And			the WIFI-Status is online
		And			the Counter is below 7
		And			the system can not find Dobby
		
		Then 		the user will see a message "Not found!"
		And			the system increases the counter 
		And 			the system looks for Dobby again 
	
	

	
	Scenario: 		The system can not find Dobby 7 times
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connection button
		And			the WIFI-Status is online
		And			the system can not find Dobby
		And 			the counter is 7 
		
		Then 		the user will see a message "Not found! Please restart Dobby"
		And 			the user can start the usecase again 
		
	
	
	
	Scenario: 		Dobby can not verify the validation key
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connection button
		And			the WIFI-Status is online
		And			the system sets Counter to 1
		And			the system finds Dobby
		And 			the system sends validation key
		And			Dobby can not verify validation key
		
		Then 		the user will see a message "Validation failed!"
		And			the system increases the counter 
		And 			the system sends validation key again 
	
	

	
	Scenario: 		Dobby can not verify the validation key 7 times
		
		Given		The user is logged in
		And			the user is on the connection page
		
		When 		the user pushes the connection button
		And			the WIFI-Status is online
		And			the system sets Counter to 1
		And			the system finds Dobby
		And 			the system sends validation key
		And			Dobby can not verify validation key
		And 			the counter is 7 
		
		Then 		the user will see a message "Validation not verified!"
		And 			the user can start the usecase again 	
	
		
