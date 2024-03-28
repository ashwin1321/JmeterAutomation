import org.apache.http.Header
import org.apache.http.message.BasicHeader
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

// Get the current directory where the script is located
def currentDir = new File('.').getCanonicalPath()

// executing the get request
def url = 'https://jsonplaceholder.typicode.com/todos?_limit=5'
def client = HttpClients.createDefault()
def get = new HttpGet(url)
get.addHeader(new BasicHeader('Connection', 'keep-alive'))
def response = client.execute(get)

// response code
log.info("Response status code: ${response.getStatusLine().getStatusCode()}")

// if response code 200 then go to the next step of saving the response
if (response.getStatusLine().getStatusCode() == 200) {
    def responseData = org.apache.commons.io.IOUtils.toString(response.getEntity().getContent(), 'UTF-8')
    log.info("Response data: ${responseData}")
    
    vars.put('GET_Response', responseData)
    
   // Generating file path dynamically
   def filePath = currentDir + '/GET_Response.json'

    // write to file
    new File(filePath).write(responseData)
    log.info("Response has been written to the dynamically generated location: ${filePath}")
} else {
    log.error("Something went wrong, the status code is: ${response.getStatusLine().getStatusCode()}")
}

client.close()
