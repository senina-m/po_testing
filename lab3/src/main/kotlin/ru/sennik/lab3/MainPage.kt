package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
class MainPage(driver: WebDriver) {

    //конструктор класса, занимающийся инициализацией полей класса
    private var driver: WebDriver

     //определение локатора поле с названием вакансии
    @FindBy(xpath = "//*[contains(@id, 'a11y-search-input')]")
    private val vacancyInput: WebElement? = null

    //определение локатора кнопки поиска
//    <button class="bloko-button bloko-button_kind-primary bloko-button_scale-large bloko-button_stretched" type="submit" data-qa="search-button">
    @FindBy(xpath = "//*[contains(@class, 'bloko-button bloko-button_kind-primary bloko-button_scale-large bloko-button_stretched')]")
    private val searchBtn: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div/div/div/div/div[5]/a")
    private val loginBtn: WebElement? = null
    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    //метод для ввода названия профессии
    fun inputVacancy(login: String?) {
        vacancyInput!!.sendKeys(login)
    }

    //метод для осуществления нажатия кнопки входа в аккаунт
    fun clickSearchBtn() {
        searchBtn!!.click()
    }

    //метод для осуществления нажатия кнопки входа в аккаунт
    fun clickLoginBtn() {
        loginBtn!!.click()
    }

    fun findLoginBtn(): Boolean{
        return loginBtn != null
    }
}