package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginWithPasswordPage (driver: WebDriver){
    private var driver: WebDriver

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div[2]/form/div[1]/fieldset/input")
    private val loginInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div[2]/form/div[2]/fieldset/input")
    private val passwordInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div[2]/form/div[5]/div/button[1]")
    private val loginButton: WebElement? = null
    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun inputLogin(login: String?) {
        loginInput?.sendKeys(login)
    }
    fun inputPasswd(passwd: String?) {
        passwordInput?.sendKeys(passwd)
    }

    fun login(){
        loginButton!!.click()
    }
}