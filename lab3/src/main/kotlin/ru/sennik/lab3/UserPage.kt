package ru.sennik.lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import java.lang.RuntimeException

class UserPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div[1]/div/div/div/div[10]/div/div[1]/div/button/span")
//    @FindBy(xpath = "/html/body/div[5]/div/div[2]/div[1]/div/div/div/div[10]/div/div[1]/div/button/span")
    private val userInfo: WebElement? = null

////    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[1]/a/span") //firefox
////    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[1]/a/span") //chrome
//    private val userName: WebElement? = null

    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[3]/div/form/button")
//    /html/body/div[12]/div/div/div[2]/div[3]/div/form/button //firefox
//    /html/body/div[12]/div/div/div[2]/div[3]/div/form/button //chrome
    private val logoutBtn : WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

//    fun getUserName(): String? {
//        return userName?.text
//    }

    fun clickUserInfo(){
        userInfo!!.click()
    }

    fun clickLogout(){
        logoutBtn!!.click()
    }

    fun clickSettings(){
        try {
            val btn12 = driver.findElement(By.xpath("/html/body/div[12]/div/div/div[2]/div[2]/a[1]"))
            btn12.click()
        }catch (_: RuntimeException){
            val btn11 = driver.findElement(By.xpath("/html/body/div[11]/div/div/div[2]/div[2]/a[1]"))
            btn11.click()
        }
    }
}