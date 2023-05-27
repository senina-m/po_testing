package ru.sennik.lab3

import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.RemoteWebDriver
import ru.sennik.lab3.ConfProperties.getProperty
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class SearchTest {
    private var driverManager = DriverManager
    private var driverList = emptyList<RemoteWebDriver>()
    private val testName = "search_test"

    @BeforeEach
    fun setup() {
        driverList = emptyList()
        driverList + (driverManager.getChromeDriver(testName))
        driverList + (driverManager.getFirefoxDriver(testName))
//        val driver = ChromeDriver()
//        System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"))
//        driver.manage().window().maximize()
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS)
//        driverList + driver
    }

    @AfterEach
    fun tearDown() {
        driverList.forEach { d -> d.quit() }
    }

    // функция, которая запускает тесты параллельно
    private fun runTest(testFun : (RemoteWebDriver) -> Unit){
        val latch = CountDownLatch(driverList.size)
        driverList.forEach { d ->
            thread {
                try {
                    testFun(d)
                } catch (e: InterruptedException) {
                    Assertions.fail<Any>()
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()
        Assertions.assertEquals(true, true)
    }

    @Test
    fun searchVacancyTest(){
        runTest(::searchVacancy)
    }

    private fun searchVacancy(driver: WebDriver){
        val mainPage = MainPage(driver)
        val searchPage = SearchPage(driver)
        mainPage.inputVacancy(getProperty("vacancy"))
        mainPage.clickSearchBtn()
        val num = getProperty("num_of_vacancies").toInt()
        val headers = searchPage.getVacanciesHeaders(num)
        for (i: Int in 0 until num){
            headers[i]?.toLowerCase()?.let { Assertions.assertTrue(it.contains(getProperty("vacancy").toLowerCase())) }
        }
    }

    @Test
    fun searchCVTest(){
        runTest(::searchCV)
    }
    private fun searchCV(driver: WebDriver){
        val mainPage = MainPage(driver)
        val searchPage = SearchPage(driver)
        mainPage.inputVacancy(getProperty("cv"))
        mainPage.clickSearchBtn()
        searchPage.searchCVs()
        val num = getProperty("num_of_cvs").toInt()
        val headers = searchPage.getCVHeaders(num)
        for (i: Int in 0 until num){
            headers[i]?.toLowerCase()?.let { Assertions.assertTrue(it.contains(getProperty("cv").toLowerCase())) }
        }
    }

    @Test
    fun searchCompanies(){
        runTest(:: searchCompanies)
    }
    private fun searchCompanies(driver: WebDriver){
        val mainPage = MainPage(driver)
        val searchPage = SearchPage(driver)
        mainPage.inputVacancy(getProperty("company"))
        mainPage.clickSearchBtn()
        searchPage.searchCompanies()
        val num = getProperty("num_of_companies").toInt()
        val headers = searchPage.getCompaniesHeaders(num)
        for (i: Int in 0 until num){
            headers[i]?.toLowerCase()?.let { Assertions.assertTrue(it.contains(getProperty("company").toLowerCase())) }
        }
    }
}