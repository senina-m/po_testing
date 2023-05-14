package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class UserPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div[1]/div/div/div/div[10]/div/div[1]/div/button")
    private val userInfo: WebElement? = null

    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[1]/a/span")
    private val userName: WebElement? = null

    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[3]/div/form/button")
    private val logoutBtn : WebElement? = null

    @FindBy(xpath = "/html/body/div[12]/div/div/div[2]/div[2]/a[1]")
    private val settingsBtn : WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun getUserName(): String? {
        return userName?.text
    }

    fun clickUserInfo(){
        userInfo!!.click()
    }

    fun clickLogout(){
        logoutBtn!!.click()
    }

    fun clickSettings(){
        settingsBtn!!.click()
    }
}