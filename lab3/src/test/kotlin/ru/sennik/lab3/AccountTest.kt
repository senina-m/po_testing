package ru.sennik.lab3

import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.chrome.ChromeDriver
import ru.sennik.lab3.ConfProperties.getProperty
import java.util.concurrent.TimeUnit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class AccountTest {

    companion object {
        private val driver = ChromeDriver()
        val searchPage = SearchPage(driver)
        val mainPage = MainPage(driver)
        val settingsPage = SettingsPage(driver)
        val loginPage = LoginPage(driver)
        val loginWithPasswordPage = LoginWithPasswordPage(driver)
        val userPage = UserPage(driver)
        @JvmStatic
        @BeforeAll
        fun setup() {
            //определение пути до драйвера и его настройка
            System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"))
            //окно разворачивается на полный экран
            driver.manage().window().maximize()
            //задержка на выполнение теста = 1 сек.
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS)
        }
    }
    @BeforeEach
    fun setupMainPage() {
        driver.get(getProperty("mainpage"))
    }

    private fun login(login: String, password: String){
        mainPage.clickLoginBtn()
        loginPage.clickLoginWithPassword()
        loginWithPasswordPage.inputLogin(login)
        loginWithPasswordPage.inputPasswd(password)
        loginWithPasswordPage.login()
    }

    @Test
    fun loginTest() {
        login(getProperty("login"), getProperty("password"))
        userPage.clickUserInfo()
        val name: String? = userPage.getUserName()
        println(name.toString())
        Assertions.assertEquals(name, getProperty("userName"))
    }

    @Test
    fun logoutTest(){
        login(getProperty("login"), getProperty("password"))
        userPage.clickUserInfo()
        userPage.clickLogout()
        //проверяем, что можем найти кнопку входа
        Assertions.assertTrue(mainPage.findLoginBtn())
    }

    private fun openSettings(){
        login(getProperty("login"), getProperty("password"))
        userPage.clickUserInfo()
        userPage.clickSettings()
    }

    @Test
    fun changeName(){
        openSettings()
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
        openSettings()
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