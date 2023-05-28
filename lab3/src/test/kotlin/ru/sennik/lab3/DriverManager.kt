package ru.sennik.lab3

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.MalformedURLException
import java.net.URL
import java.time.Duration
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object DriverManager {
    private fun opts(name: String): Map<String, Any> {
        val opts = HashMap<String, Any>()
        opts["name"] = name
        opts["sessionTimeout"] = "15m"
        opts["env"] = listOf("TZ=UTC")
        opts["labels"] = java.util.Map.of("manual", "true")
        opts["enableVideo"] = true
        opts["videoName"] = name + "-" + LocalDate.now() + ".mp4"
        opts["enableVNC"] = true
        return opts
    }

    @Throws(MalformedURLException::class)
    fun getChromeDriver(name: String): RemoteWebDriver {
        val chromeDriver: RemoteWebDriver
        val chromeOptions = ChromeOptions()
        chromeOptions.setCapability("browserVersion", "112.0")
        chromeOptions.setCapability("selenoid:options", opts("$name-chrome"))
        chromeOptions.addArguments("--remote-allow-origins=*")
        chromeDriver = RemoteWebDriver(URL("http://localhost:4444/wd/hub"), chromeOptions)
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
        WebDriverWait(chromeDriver, Duration.of(5, ChronoUnit.SECONDS)).until { webDriver: WebDriver ->
            "complete" == (webDriver as JavascriptExecutor).executeScript(
                "return document.readyState"
            )
        }
        return chromeDriver
    }

    @Throws(MalformedURLException::class)
    fun getFirefoxDriver(name: String): RemoteWebDriver {
        val firefoxDriver: RemoteWebDriver
        val firefoxOptions = FirefoxOptions()
        firefoxOptions.setCapability("browserVersion", "112.0")
        firefoxOptions.setCapability("selenoid:options", opts("$name-firefox"))
        firefoxDriver = RemoteWebDriver(URL("http://localhost:4444/wd/hub"), firefoxOptions)
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
        WebDriverWait(firefoxDriver, Duration.of(5, ChronoUnit.SECONDS)).until { webDriver: WebDriver ->
            "complete" == (webDriver as JavascriptExecutor).executeScript(
                "return document.readyState"
            )
        }
        return firefoxDriver
    }
}