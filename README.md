# Beymen Shopping Test Project
This project is a Selenium WebDriver project that includes automation tests on Beymen shopping site. Below is detailed information about the project and instructions for use.

The project includes the following steps.

- Open www.beymen.com website.
- It is checked that the home page is opened.
- Enter the word "shorts" in the search box. (Note = Shorts word product.xlsl file is written by taking 1 column from 1 line. )
- The word "shorts" entered in the search box is deleted.
- The word "shirt" is entered in the search box. (Note = Shirt word product.xlsl file is written by taking 2 columns from 1 line).
- Press the "enter" key on the keyboard
- According to the result, a random product is selected from the products displayed.
- Product information and amount information of the selected product is written to the txt file.
- The selected product is added to the cart.
- The price on the product page is compared with the price of the product in the cart.
- By increasing the quantity, it is verified that the product quantity is 2.
- The product is deleted from the cart and it is checked that the cart is empty.

## Project Structure

The project consists of the following packages and classes:

- **builders:** Hooks class for initializing and configuring the Selenium WebDriver, and the OSName enum class that depends on this class.

- **models:** `Number` and `Product` classes containing the data models to be used in the tests.

- **pages:** There are page classes that contain the elements of web pages and the operations performed on these pages. `CommonPage`, `HomePage`, `SearchPage`, and `BasketPage`classes contain page specific operations.

- **tests:** BeymenShoppingTest class contains test scenarios. This class contains a test case that simulates the entire shopping process.

- **utils:** This is a package of utility classes. 
- - `ConfigReader` class reads data from the configuration file (configuration.properties). 
- - `FunctionUtils` class contains general purpose helper methods. 
- - `LoggerUtil` class performs logging operations. The ScreenShotUtils class contains operations for taking screenshots.



## Usage

### Installing Dependencies:
The dependencies required for the project to run must be installed. These dependencies are specified in the pom.xml file. To install the dependencies using Maven, you can type the following command in the terminal or command client:

```bash
mvn clean install
```

### WebDriver Configuration:
The `getOSNameAndSetProperty` method in the Hooks class determines the correct location of the WebDriver according to the operating system. For this reason, the operation of this method should be adjusted according to the operating system the project is running on.


### Running the Test Case:
You can start the test case by running the BeymenShoppingTest class. The beymenShoppingTest method in this class performs a series of operations on the Beymen shopping site. You can check the logs and screenshots to see if the test was successful.

### Screenshots
Screenshots are saved under the `Screenshots` folder with the corresponding steps. You can check whether the test is working correctly by reviewing the screenshots taken at each step.

### Logs
Logs are saved in the file `src/test/logs/mylog.log`. You can monitor the status of the test steps by reviewing this log file.

### Configuration File:
The `src/configuration.properties` file contains the project configuration settings. From this file you can edit the startup URL and other configuration settings for the test to run.


## Notes:
ChromeDriver must be installed on your computer and the `webdriver.chrome.driver` system property must be set correctly for the tests to run.