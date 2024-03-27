import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients

// read from saved json file
def responsePath = 'C:/Users/ashwi/Documents/GET_Response.json'   // please adjust as required
def getResponse = new File(responsePath).text

// parse to json
def respionseJson = new JsonSlurper().parseText(getResponse)

// for even item negate the completion value
respionseJson.each { item ->
    if (item.id % 2 == 0 && item.userId % 2 != 0) {
        item.completed = !item.completed
    }
}

// save the file 
def saveResponsePath = 'C:/Users/ashwi/Documents/POST_Request.json'   // adjust according to your need
def responseToSave = JsonOutput.toJson(respionseJson)
new File(saveResponsePath).write(responseToSave)


// now sending the post request with tweaked json value above
def client = HttpClients.createDefault()
def post = new HttpPost('https://jsonplaceholder.typicode.com/todos')
post.addHeader('Content-Type', 'application/json')
post.setEntity(new StringEntity(responseToSave))

// Execute the POST request
def response = client.execute(post)
def responseCode = response.getStatusLine().getStatusCode()
// verify response code
log.info("POST request response code: ${responseCode}")

// getting the responseContent 
def responseContent = new JsonSlurper().parse(response.getEntity().getContent())

client.close()


// now we check if the response from post req is same as the post data sent to the api
def isValidResponse = false

// if posted we get 201 response, so lets verify if its posted or not
if (responseCode == 201) {

// the response had an extra id:201 object so lets remove it for further verification 
responseContent.remove("id")
    log.error("ressgasga" + responseContent)
    log.error("post conmtent" +  respionseJson)
    if (responseContent.size() == respionseJson.size()) {

        isValidResponse = true
        // loop through the response from API and get the id and find it in the saved post response file, if same check the competed flag to verify if the both data are same or not
        // the response from post had index key, so it is not exact as the post content so we are checking the corresponding value of every  object from its id as id is unique
        responseContent.each { key, responseItem ->
    def postedItem = respionseJson.find { it?.id == responseItem?.id } 
    if (postedItem == null || postedItem.completed != responseItem.completed) {
        isValidResponse = false
        return 
    }
}
    } else {
        isValidResponse = false
    }
} else {
    log.error("Something went wrong, response code: ${responseCode}")
}

if (isValidResponse) {
    log.info("Response content matches the posted content......")
} else {
    log.error("Response content does not match the posted content.......")
}

