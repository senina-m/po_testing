package ru.sennik.lab3

import org.junit.jupiter.api.*
import org.openqa.selenium.remote.RemoteWebDriver
import ru.sennik.lab3.ConfProperties.getProperty
import ru.sennik.lab3.Currency.Companion.toCurrency
import java.util.concurrent.TimeUnit

/**
 * @author Natalia Nikonova
 */
class CVPageTest {
    private var driverManager = DriverManager
    private var driverList = emptyList<RemoteWebDriver>()
    private val testName = "CV_test"

    @BeforeEach
    fun setup() {
        driverList = mutableListOf(driverManager.getChromeDriver(testName), driverManager.getFirefoxDriver(testName))
        driverList.forEach {
            it.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS)
            it.get(getProperty("mainpage"))
            val loginPage = LoginPage(it)
            val loginWithPasswordPage = LoginWithPasswordPage(it)
            val mainPage = MainPage(it)
            login(getProperty("login"), getProperty("password"), mainPage, loginPage, loginWithPasswordPage)
            mainPage.clickGoToCVButton()
        }
    }

    @AfterEach
    fun tearDown() {
        driverList.forEach { d -> d.quit() }
    }

    @Test
    fun createWithoutRequiredFieldFailedTest() {
        runTest(::createWithoutRequiredFieldFailed, driverList)
    }

    private fun createWithoutRequiredFieldFailed(driver: RemoteWebDriver) {
        val listCVPage = ListCVPage(driver)
        listCVPage.clickCreateCVButton()
        val cvPage = CVPage(driver)

        checkNotVisibleAdditionInfo(cvPage)
        cvPage.sendCV()
        Assertions.assertTrue(cvPage.checkChoseWorkErrorMessage())
    }

    @Test
    fun createWithWorkExperienceSuccessful() {
        runTest(::createWithWorkExperienceSuccessful, driverList)
    }
    private fun createWithWorkExperienceSuccessful(driver: RemoteWebDriver) {
        val listCVPage = ListCVPage(driver)
        listCVPage.clickCreateCVButton()

        val cvPage = CVPage(driver)
        val successfulCreateCVPage = SuccessfulCreateCVPage(driver)
        val cvViewPage = CVViewPage(driver)
        // что не доступны поля которые после выбора опыта открываются
        checkNotVisibleAdditionInfo(cvPage)
        // проверить что поля по умолчанию добавились правильно
        //checkDefaultValues()
        // выбрать чтоесть опыт работы
        cvPage.selectWithWorkExperience()

        // проверить что не доступны поля для нет опыта
        Assertions.assertTrue(cvPage.checkNotExistNoWorkExperienceFields())
        // заполнить минимум
        cvPage.fillSpecialization(getProperty("post"), getProperty("salary").toInt(), getProperty("currency").toCurrency()!!)
        cvPage.addWork(
                getProperty("startWorkMonth").toInt(),
                getProperty("startWorkYear").toInt(),
                getProperty("organization"),
                getProperty("position"),
                getProperty("charge")
        )

        cvPage.sendCV()

        // проверить что отправилось успешно
        Assertions.assertTrue(successfulCreateCVPage.checkSuccessHeader())
        // удалить
        clearCV(getProperty("post"), successfulCreateCVPage, listCVPage, cvViewPage)
    }

    @Test
    fun createWithoutWorkExperienceSuccessful() {
        runTest(::createWithoutWorkExperienceSuccessful, driverList)
    }

    private fun createWithoutWorkExperienceSuccessful(driver: RemoteWebDriver) {
        val listCVPage = ListCVPage(driver)
        listCVPage.clickCreateCVButton()

        val cvPage = CVPage(driver)
        val successfulCreateCVPage = SuccessfulCreateCVPage(driver)
        val cvViewPage = CVViewPage(driver)
        // что не доступны поля которые после выбора опыта открываются
        checkNotVisibleAdditionInfo(cvPage)
        // проверить что поля по умолчанию добавились правильно
        checkDefaultValues(cvPage)

        // выбрать что нет опыт работы
        cvPage.selectWithoutWorkExperience()

        // проверить что не доступны поля для есть опыт
        Assertions.assertTrue(cvPage.checkNotExistWorkExperienceFields())
        // заполнить минимум
        cvPage.fillSpecialization(getProperty("other_post"), getProperty("salary").toInt(), getProperty("currency").toCurrency()!!)
        cvPage.sendCV()

        // проверить что отправилось успешно
        Assertions.assertTrue(successfulCreateCVPage.checkSuccessHeader())
        // удалить
        clearCV(getProperty("other_post"), successfulCreateCVPage, listCVPage, cvViewPage)
    }

    private fun login(login: String, password: String, mainPage: MainPage, loginPage: LoginPage, loginWithPasswordPage: LoginWithPasswordPage) {
        mainPage.clickLoginBtn()
        loginPage.clickLoginWithPassword()
        loginWithPasswordPage.inputLogin(login)
        loginWithPasswordPage.inputPasswd(password)
        loginWithPasswordPage.login()
    }

    private fun checkNotVisibleAdditionInfo(cvPage: CVPage) {
        Assertions.assertTrue(cvPage.checkNotExistEducationFields())
        Assertions.assertTrue(cvPage.checkNotExistSpecialtyFields())
        Assertions.assertTrue(cvPage.checkNotExistsLanguagesFields())
        Assertions.assertTrue(cvPage.checkNotExistAddWorkFields())
        Assertions.assertTrue(cvPage.checkNotExistNoWorkExperienceFields())
        Assertions.assertTrue(cvPage.checkNotExistWorkExperienceFields())
    }

    private fun checkDefaultValues(cvPage: CVPage) {
        Assertions.assertEquals(cvPage.getNameFieldText(), getProperty("name"))
        Assertions.assertEquals(cvPage.getSurnameFieldText(), getProperty("surname"))
        // Assertions.assertEquals(cvPage.getCityFieldText(), getProperty("city"))
        Assertions.assertEquals(cvPage.getSex().name, getProperty("sex"))
    }

    private fun clearCV(name: String, successfulCreateCVPage: SuccessfulCreateCVPage, listCVPage: ListCVPage, cvViewPage: CVViewPage) {
        successfulCreateCVPage.clickGoToCVButton()
        listCVPage.openCVByName(name)
        cvViewPage.clickDeleteButton()
        cvViewPage.clickConfirmDeleteButton()
    }
}