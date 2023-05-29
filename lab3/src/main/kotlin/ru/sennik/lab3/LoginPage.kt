package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginPage (driver: WebDriver){
    private var driver: WebDriver

//    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div/form/div[5]/button[2]")
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div/div[2]/form/div[5]/button[2]")
    private val loginWithPass: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun clickLoginWithPassword(){
        loginWithPass!!.click()
    }
}