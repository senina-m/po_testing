package ru.sennik.lab3

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import ru.sennik.lab3.ConfProperties.getProperty
import java.net.URL
import org.openqa.selenium.remote.RemoteWebDriver
import java.util.concurrent.TimeUnit
import org.junit.jupiter.api.Assertions


class SimpleTest(){
    companion object{
        val options = ChromeOptions().also {
          it.setCapability("browserVersion", "113.0")
          it.setCapability("selenoid:options", mapOf(
          "name" to "Simple Test",
          "sessionTimeout" to "15m",
          "env" to listOf("TZ=UTC"),
          "labels" to mapOf("manual" to "true"),
          "enableVideo" to true,
          "enableVNC" to true
          ))
        }

        val driver = RemoteWebDriver(URL("http://localhost:4444/wd/hub"), options)
        // val driver = ChromeDriver()
        val mainPage = MainPage(driver)

      @JvmStatic
      @AfterAll
      fun tireDown() {
        driver.quit()
      }

      @JvmStatic
      @BeforeAll
      fun setup() {
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS)
        driver.get(getProperty("mainpage"))
      }
    }

    @Test
    fun simpleTest(){
        val mainPage = MainPage(driver)
        mainPage.clickSearchBtn()
        Assertions.assertTrue(true);
    }
}