# SearchSuggestions
This application has REST endpoints expose to upload the file having city directory data and do search for city that start will provided string and return only atmost result(s) that we wanted as plan text. You can find more details in below example.
 
Application Tech Stack and Data: 
* Spring Boot Application.
* H2 database
* swagger2 : for REST endpoint testing
* Sample data file will be found  under '/SearchSuggestionsTask/datafile/Pincode_30052019.csv' to upload before search.

<b>Steps to Run and Test this Application:</b> 
From Project Root directory run below command(Maven should be installed on system):

1) mvn clean install
2) cd target
3) target> java -jar SearchSuggestions-0.0.1-SNAPSHOT.jar
4) Access Rest End points from browser using the swagger2 api : http://localhost:8080/swagger-ui.html#/
5) Endpoint to upload data file that has city information : /city_directory/api/uploadfile
	* Will get response as 'File Uploaded Successfully !' once upload file is successfull otherwise an error message.
6) Endpoint to perform the city search : /search_directory/suggest_cities
	* e.g http://localhost:8080/search_directory/suggest_cities?start=che&atmost=5
	* Would result 'chennai' in reponse body 
	* Additionally, http://localhost:8080/search_directory/suggest_cities?start=ch&atmost=5
	* Would result below output: <br/>
		*chamarajanagar<br/>
		*chamba<br/>
		*chamoli<br/>
		*champawat<br/>
		*champhai<br/>


