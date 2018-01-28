# rest-to-fhir-json-example
Example code for the conversion of libreHealth Patient resource to FHIR patient resource using JOLT

#### Run Code
 
 Clone repository from github
 
 ``
 git clone https://github.com/lehone-hp/rest-to-fhir-json-example.git              
 ``
  
 Import into IDE and run
 
 #### Or
 
 Build project
 
 `
 cd rest-to-fhir-json
 `

 `
 mvn clean package
 `
 
 Run with 
 
 `
 java -cp java -cp target/json-converter-1.0-SNAPSHOT.jar org.librehealth.converter.Main
 `
 
