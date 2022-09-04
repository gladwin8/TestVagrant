# Pushpa - The Rise (Imdb & Wikipedia)

Its a maven project, Dependencies used in pom.xml --> Selenium-Java,TestNG and WebDriverManager

Below are the Concepts implemented in the project 
1. Under **Page Object Model** package , 2 classes been created based on the validation where locators(xpath,id,etc) been passed through By abstract class 
2. TestNG \
   -**Annotations** - BeforeClass ,AfterClass,DataProvider,Test \
   -**Listeners class** - Taking screenshot when there is a issue in application \
   -**Assertions** - AssertEquals method,softassert \
   -Running the script under one suite through **testng.xml**\
3. Declaring URLs(Imdb,Wikipedia) , Wait time ,Browser name in **global.Properties** file \
4. Common functionalities been listed out in method like **browserInitiation** , **getScreenshot** which is been declared in **Base** class under Resource package \
5. Through Selenium with Java all the above libraries gets integrated and Under **EndtoEnd** class file selenium concepts like Driver initiation,Implicit,Explict wait,effectively handled auto suggestions drop down,data comparison,etc, \

## Test Flow: 
The whole testflow is been controlled in EndtoEnd.java under Functionallibraries package where Imdb_Wiki_validation method acts like main method  \
  -Each steps are listed in seperate method which creates a seperate flow for execution.Below are the methods , \
    *imdbValidation_homepage()* --> User navigates to IMDB url & Validate the label ; Navigate to the mentioned movie details \
    *imdbMovieValidation()* --> Extracts the data for Release details , Country of origin and stores in hashmap which can be used for comparison \
    *Wiki_Homepage()* --> User navigates to Wikipedia url & Validate the label ; Navigate to the mentioned movie details \
    *Wiki_MovieValidation()* --> Similar to imdbMovieValidation method but happens in Wikipedia page and data stored in hashmap where it gets compared. \
		
### Steps to clone execute the tests
git clone https://github.com/gladwin8/TestVagrant
cd TestVagrant
mvn clean test 
