 package ru.sennik.lab3

 import org.junit.jupiter.api.*
 import ru.sennik.lab3.ConfProperties.getProperty
 import java.util.concurrent.TimeUnit
 import org.openqa.selenium.remote.RemoteWebDriver

 class AccountTest {

     private var driverManager = DriverManager
     private var driverList = emptyList<RemoteWebDriver>()
     private val testName = "search_test"

     @BeforeEach
     fun setup() {
         driverList = mutableListOf(driverManager.getChromeDriver(testName), driverManager.getFirefoxDriver(testName))
         driverList.forEach{
             it.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS)
             it.get(getProperty("mainpage"))
         }
     }

     @AfterEach
     fun tearDown() {
         driverList.forEach { d -> d.quit() }
     }

     private fun login(login: String, password: String, mainPage: MainPage, loginPage: LoginPage, loginWithPasswordPage: LoginWithPasswordPage){
         mainPage.clickLoginBtn()
         loginPage.clickLoginWithPassword()
         loginWithPasswordPage.inputLogin(login)
         loginWithPasswordPage.inputPasswd(password)
         loginWithPasswordPage.login()
     }

     @Test
     fun loginTest() {
         runTest(::loginT, driverList)
     }

     private fun loginT(driver: RemoteWebDriver) {
         val mainPage = MainPage(driver)
         val loginPage = LoginPage(driver)
         val loginWithPasswordPage = LoginWithPasswordPage(driver)
         val userPage = UserPage(driver)

         login(getProperty("login"), getProperty("password"), mainPage, loginPage, loginWithPasswordPage)
         userPage.clickUserInfo()
         val name: String? = userPage.getUserName()
         println(name.toString())
         Assertions.assertEquals(name, getProperty("userName"))
     }

     @Test
     fun logoutTest(){
         runTest(::logout, driverList)
     }

     private fun logout(driver: RemoteWebDriver){
         val mainPage = MainPage(driver)
         val loginPage = LoginPage(driver)
         val loginWithPasswordPage = LoginWithPasswordPage(driver)
         val userPage = UserPage(driver)
         login(getProperty("login"), getProperty("password"), mainPage, loginPage, loginWithPasswordPage)
         userPage.clickUserInfo()
         userPage.clickLogout()
         //проверяем, что можем найти кнопку входа
         Assertions.assertTrue(mainPage.findLoginBtn())
     }

     private fun openSettings(mainPage: MainPage, loginPage: LoginPage, loginWithPasswordPage: LoginWithPasswordPage, userPage: UserPage){
         login(getProperty("login"), getProperty("password"), mainPage, loginPage, loginWithPasswordPage)
         userPage.clickUserInfo()
         userPage.clickSettings()
     }

     @Test
     fun changeNameTest(){
         runTest(::changeName, driverList)
     }

     private fun changeName(driver: RemoteWebDriver){
         val mainPage = MainPage(driver)
         val loginPage = LoginPage(driver)
         val loginWithPasswordPage = LoginWithPasswordPage(driver)
         val userPage = UserPage(driver)
         val settingsPage = SettingsPage(driver)
         openSettings(mainPage, loginPage, loginWithPasswordPage, userPage)
         settingsPage.clickChangeName()
         settingsPage.inputName(getProperty("new_name"))
         settingsPage.inputSurname(getProperty("new_surname"))
         settingsPage.input2Name(getProperty("new_name2")) //optional
         settingsPage.clickSaveName()
         val name = settingsPage.getUserName()
         println(settingsPage.getUserName().toString())
         Assertions.assertEquals("${getProperty("new_surname")} ${getProperty("new_name2")} ${getProperty("new_name")}", name)

         //set old name
         settingsPage.clickChangeName()
         settingsPage.inputName(getProperty("name"))
         settingsPage.inputSurname(getProperty("surname"))
         settingsPage.input2Name(getProperty("name2")) //optional
         settingsPage.clickSaveName()
     }
     @Test
     fun changePassword(){
         runTest(::changePassword, driverList)
     }
     private fun changePassword(driver: RemoteWebDriver){
         val mainPage = MainPage(driver)
         val loginPage = LoginPage(driver)
         val loginWithPasswordPage = LoginWithPasswordPage(driver)
         val userPage = UserPage(driver)
         val settingsPage = SettingsPage(driver)
         openSettings(mainPage, loginPage, loginWithPasswordPage, userPage)
         settingsPage.clickChangePassword()
         settingsPage.inputOldPassword(getProperty("password"))
         val newPass = getProperty("new_password")
         settingsPage.inputNewPassword1(newPass)
         settingsPage.inputNewPassword2(newPass)
         settingsPage.clickSavePassword()
         // the password was changed
         val notification: String? = settingsPage.getPasswordChangedNotification()
         Assertions.assertEquals(notification, getProperty("notification_pass_success"))

         //change password back
         settingsPage.clickChangePassword()
         settingsPage.inputOldPassword(getProperty("new_password"))
         settingsPage.inputNewPassword1(getProperty("password"))
         settingsPage.inputNewPassword2(getProperty("password"))
         settingsPage.clickSavePassword()
     }
 }