
## JMeter API Automation

## Installation
### Install Java if not installed
- go to google and install
### Install Apache JMeter
- got to google and install
### download the jmx files from the repository

## Usage
### Description
- Here, two jmx scripts are created, one gets the response form the api and saves in the local device and other reads the locally stored response and tweaks the value and posts the data in the api. Finally we compare if the response from post is same as we tweaked or not.

- Here, we are using httpClient to interact with the api and get the response, and other analysis is done as required.

### Open Apache Jmeter



### Running the application

Running the Application
1. Get Response Script
-  go to files > open 
- open the TestPlanGetResponse.jmx file
- run the test file
- to verify the response code go to view result tree and check if test case passed or not
- to see the json file imported, go to logs, logs has the information of where the file is saved.

2. Post and Compare Response Script
- go to files > open 
- Open the TestPlanGetResponse.jmx file
- Run the test
- to verify response code go to view result tree and check if test case passed where response code is 201.
- Also to check if the response from the post request is same as the tweaked content, a flag is set in the jmeter variable
- in the same view result tree go to the debug sampler test, and go to  response data and check the flag **Is response from post request and posted content same**. If true the content is same else false. also you can check content in variables **response from post** and **tweaked content before post**
