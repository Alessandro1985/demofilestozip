# demofilestozip

1) Run the application: 
   You can run: "$ docker build . --tag demofilestozip" (if you use Docker) or open a command line (in project directory) and type: "gradlew bootRun"
2) Use the follow curl request in order to send a list of files:
   curl --location --request POST 'http://localhost:8080/files/addToZip' \
        --form 'file1=@<PATH TO YOUR FILE>' \
        --form 'file2=@"<PATH TO YOUR FILE>"' \
        --form 'file3=@"<PATH TO YOUR FILE>"'
  
  Or using Postman
  
  
  ![image](https://user-images.githubusercontent.com/7733926/111084235-4e500900-8509-11eb-8d3a-f246397d75b5.png)
  
3) The response should be 200 ok with a CreatedZip entity that contains a zipName ="zip" and  a zipFile field which is a ZipOutPutStream that contains
   the 3 files sent i nthe request
   ![image](https://user-images.githubusercontent.com/7733926/111084398-17c6be00-850a-11eb-8b1e-4cd56e14b911.png)
