Feature:Test the Andriod Mobile using Appium

	Scenario:
	 Given Appium Server is running 
	 And Android emulator or the device is up and running
	 And Desired capabilities are set and application file "C:\\Users\\Imran11296\\Downloads\\selendroid-test-app-0.14.0.apk" loaded on appium
	 When the url "http://127.0.0.1:4723/wd/hub" is hit and App is loaded
	 And then enter the Mobile version "Nexus 5" in textbox
	 And click the Checkbox
	 Then the textbox should contain the text "Nexus 5"
	 And checkbox should have been unchecked.