## JMeter API Automation

## Installation

### Install Java if not installed

- go to google and install

### Install Apache JMeter

- got to google and install

## Usage

### Description

- Here, two scripts are created, one gets the response form the api and saves in the local device and other reads the locally stored response and tweaks the value and posts the data in the api. Finally we compare if the response from post is same as we tweaked or not.

- Here, we are using httpClient to interact with the api and get the response.

### Open Apache Jmeter

- right click Test Plan > Add > Threads > Thread Group
- right click Thread Group > Add > Sampler > JSR223 Sampler > name it Get Response
- create another JSR223 Sampler from Thread group and name it Post and Compare Response

### Running the application

Running the Application

1. Get Response Script

- Open the Get Response file.
- In the prompt area, paste the getResponseAndSave.groovy script.
- Adjust the file paths in the code according to your requirements.
- Run the script.
- Go to the specified location and open the JSON file to verify the data.

2. Post and Compare Response Script

- Open the Post and Compare Response file.
- In the prompt area, paste the postAndCompareResponse.groovy script.
- Adjust the file paths in the code according to your requirements.
- Run the script.
- Verify in the logs if the compared data matches or not.
