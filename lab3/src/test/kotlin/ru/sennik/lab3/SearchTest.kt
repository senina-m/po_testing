package ru.sennik.lab3

import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.RemoteWebDriver
import ru.sennik.lab3.ConfProperties.getProperty
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors
import java.util.concurrent.ExecutionException
import kotlin.concurrent.thread


class SearchTest {
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

    // функция, которая запускает тесты параллельно
    private fun runTest(testFun : (RemoteWebDriver) -> Unit){
        val executor = Executors.newFixedThreadPool(driverList.size)
        val results = driverList.map { d ->
            executor.submit{
                testFun(d)
            }
        }
        executor.shutdown()
//        executor.awaitTermination(15, TimeUnit.SECONDS)
        results.forEach{
            try{
                it.get()
            }
            catch (e : ExecutionException){
                println("ExecutionException: ${e.message}")
                Assertions.fail<Any>()
            }
            catch (e : InterruptedException){
                println("InterruptedException: ${e.message}")
                Assertions.fail<Any>()
            }
        }
    }

    @Test
    fun searchVacancyTest(){
        runTest(::searchVacancy)
    }

    private fun searchVacancy(driver: WebDriver){
//        throw InterruptedException("fail test")
         val mainPage = MainPage(driver)
         val searchPage = SearchPage(driver)
         mainPage.inputVacancy(getProperty("vacancy"))
         mainPage.clickSearchBtn()
         val num = getProperty("num_of_vacancies").toInt()
         val headers = searchPage.getVacanciesHeaders(num)
         for (i: Int in 0 until num){
             headers[i]?.lowercase()?.let { Assertions.assertTrue(it.contains(getProperty("vacancy").lowercase())) }
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
            headers[i]?.lowercase()?.let { Assertions.assertTrue(it.contains(getProperty("cv").lowercase())) }
        }
    }

    @Test
    fun searchCompaniesTest(){
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
            headers[i]?.lowercase()?.let { Assertions.assertTrue(it.contains(getProperty("company").lowercase())) }
        }
    }
}