package ru.sennik.lab3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import ru.sennik.lab3.ConfProperties.getProperty
import java.util.concurrent.TimeUnit

class SearchTest {
    companion object {
        private val driver = ChromeDriver()
        val searchPage = SearchPage(driver)
        val mainPage = MainPage(driver)
        @JvmStatic
        @BeforeAll
        fun setup() {
            //определение пути до драйвера и его настройка
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"))
            //окно разворачивается на полный экран
            driver.manage().window().maximize()
            //задержка на выполнение теста = 1 сек.
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS)
        }
    }
    @BeforeEach
    fun setupMainPage() {
        driver.get(ConfProperties.getProperty("mainpage"))
    }
    @Test
    fun searchVacancy(){
        mainPage.inputVacancy(getProperty("vacancy"))
        mainPage.clickSearchBtn()
        val num = getProperty("num_of_vacancies").toInt()
        val headers = searchPage.getVacanciesHeaders(num)
        for (i: Int in 0 until num){
            headers[i]?.toLowerCase()?.let { Assertions.assertTrue(it.contains(getProperty("vacancy").toLowerCase())) }
        }
    }
    @Test
    fun searchCV(){
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