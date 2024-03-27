import org.apache.http.Header
import org.apache.http.message.BasicHeader
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

// executing the get request
def url = 'https://jsonplaceholder.typicode.com/todos?_limit=5'
def client = HttpClients.createDefault()
def get = new HttpGet(url)
get.addHeader(new BasicHeader('Connection', 'keep-alive'))
def response = client.execute(get)

// response code
log.info("Response status code: ${response.getStatusLine().getStatusCode()}")

// if response code 200 then go to next step of saving the response
if (response.getStatusLine().getStatusCode() == 200) {
    def responseData = org.apache.commons.io.IOUtils.toString(response.getEntity().getContent(), 'UTF-8')
    log.info("Response data: ${responseData}")
    
    vars.put('GET_Response', responseData)
    
   // saving location 
   def filePath = 'C:/Users/ashwi/Documents/GET_Response.json'    // please adjust location as required

    // write to file
    new File(filePath).write(responseData)
    log.info("response has been written to the defioned location")
} else {
    log.error("Something went wrong, the status code is: ${response.getStatusLine().getStatusCode()}")
}

client.close()