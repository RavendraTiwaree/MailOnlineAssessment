This Test Automation framework project uses Selenium and Cucumber with Java as the programming language. This project contains both UI and API related scenario. 


UI - Selenium, Cucumber, BDD Approach, Java, Maven, Page object model

API - Rest Assured library

This Framework is developed by Ravendra Tiwaree(ravendra.ait@gmail.com)

Points need to know before running the script
1. All kind of test data is available in testDataGeneral class
2. Use the CodeDecodeEncodePassword java class to create encrypted password
3. To Specify the browser for execution, Please opt in testDataGeneral class
4. To run the both api and UI test case together please pass the Tag @Regression in TestRunner class under tags = "@Regression". For individual execution of the cases separate tag need to assign
![](D:\TestRunner.jpg)


5. Dependency are managed in POM.XML file
6. Flow of design pattern Feature File ->StepDefinition->PageObject->Methods
7. Parameterization has been taken care based on instruction. Please refer the feature file![](D:\FeatureFile_MailOnline.jpg)
