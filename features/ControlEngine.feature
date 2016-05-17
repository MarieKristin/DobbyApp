


Feature: Control Engine feature

		As 				an user
		I want 			to choose the control mode
		so that			Dobby can drive automatically or manually 
		

	Scenario: 		Choosing manual mode successfullly
		
		Given		The user is logged in
		And			the user is on the Control Engine dashboard
		And			the system is connected to Dobby
	
		When 		the user pushes the button for manual mode
		And			the system activates Gyrosensor
		And			the system shows control overview
		
		Then		the user can steer Engine
		And			the user can accelerate Engine
		And			Dobby sets Engine Data
		

		
		
	Scenario: 		Choosing automatic mode successfullly
		
		Given		The user is logged in
		And			the user is on the Control Engine dashboard
		And			the system is connected to Dobby
		
		
		When 		the user pushes the button for automatic mode
		And			Dobby activates Webcam
		And			Dobby sets counter to 1
		And			Dobby scans pattern
		And			Dobby matches pattern
		And 		the user confirms result
		
		Then		Dobby sets Engine Data
	
	
	
	
	Scenario: 		Choosing automatic mode, can not find pattern and increases the counter
		
		Given		The user is logged in
		And			the user is on the Control Engine dashboard
		And			the system is connected to Dobby
		
		When 		the user pushes the button for automatic mode
		And			Dobby activates Webcam
		And			Dobby scans for pattern
		And			Dobby can not find pattern
		
		
		Then		Dobby increases the counter
		And			Dobby scans for pattern again.
	
	

	
	Scenario: 		Dobby can not find pattern in automatic mode 7 times
		
		Given		The user is logged in
		And			the user is on the Control Engine dashboard
		And			the system is connected to Dobby
		
		When 		the user pushes the button for automatic mode
		And			Dobby activates Webcam
		And			Dobby scans for pattern
		And			Dobby can not matching pattern
		And 		the counter is 7 
		
		Then 		the user will see a message "Pattern not found!"
		And 		the user can start the usecase again 
		
	
	
	
	Scenario: 		Dobby finds the pattern and the user denies the match
		
		Given		The user is logged in
		And			the user is on the Control Engine dashboard
		And			the system is connected to Dobby
		
		When 		the user pushes the button for automatic mode
		And			Dobby activates Webcam
		And			Dobby sets counter to 1
		And			Dobby scans for pattern
		And			Dobby matches pattern
		And 		the user denies the result
		
		Then		Dobby sets the counter to 1 
		And			Dobby scans for pattern again
		
	
	
	

	
