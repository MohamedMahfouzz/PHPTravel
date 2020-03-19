# PHPTravel

Using Selenium and TestNG, PHPTravel testing suite was created.

The Suite consists mainly of 3 Testing scenarios that can be increased according to requirements, the scenarios will be explained as follows:
  1) Positive testing: The first scenarios tests the website for the normal needed requirements for regustration and Log in using valid data.
  
  2) Negative testing(1): The second scenario tests the registration page with invalid data, in the test case different strings are used for the password and confirm password feilds. This scenario can be enriched with cases where other invald data are inserted in different cells.
  
  3) Negative testing(2): The third scenario tests the login screen using invalid data.
  
For the negative scenarios, screenshots of the resulting screen are saved according to test name.

Limitations:
  1) No HTTP interceptors were implemented
  2) Custom reports could not be created
  
Components:
  1) Testng.xml to run the test suite
  2) Classes to implement tests:
    a) Initialize
    b) Register
    c) Login
    d) ListenerTest
    e) InitializeNeg
    f) RegisterNeg
    g) LoginNeg
