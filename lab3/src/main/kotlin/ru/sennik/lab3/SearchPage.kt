package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


class SearchPage(driver: WebDriver) {
    private var driver: WebDriver

    // заголовок поиска
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div[1]/h1")
    private val searchHeader: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    val vacancyHeader: String
        get() = searchHeader!!.text
}