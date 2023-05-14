package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SettingsPage(driver: WebDriver) {
    private var driver: WebDriver

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[1]/div/div[2]/div/div/div[2]/a")
    private val changeNameHref: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[1]/div/div[2]/div/div/div[1]")
    private val nameLabel: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[2]/div[1]/div/div[2]/div/div/div[2]/a")
    private val changePasswordHref: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[4]/div[1]/div/div[2]/div/div/div[1]/span[1]")
    private val phoneLabel: WebElement? = null

    // name
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[2]/form/div[1]/div[2]/div/div/input")
    private val changeNameInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[2]/form/div[1]/div[2]/div/div/input")
    private val changeSunameInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[2]/form/div[3]/div[2]/div/div/input")
    private val change2NameInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[1]/div[2]/form/div[5]/div[2]/div/input")
    private val saveNameBtn: WebElement? = null

    //password
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[2]/div[2]/form/div[1]/div[2]/div[1]/div/div/input")
    private val oldPasswordInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[2]/div[2]/form/div[2]/div[2]/div/div/div/input")
    private val newPassword1Input: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[2]/div[2]/form/div[3]/div[2]/div/div/div/input")
    private val newPassword2Input: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div[3]/div/div[2]/div[2]/form/div[5]/div[2]/div/input")
    private val savePasswordBtn: WebElement? = null

    @FindBy(xpath = "/html/body/div[8]/div[1]/div/div/div[2]/div[1]")
    private val notificationPassChanged: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun getUserName(): String? {
        return nameLabel?.text
    }

    fun getPasswordChangedNotification(): String? {
        return notificationPassChanged?.text
    }


    fun clickChangeName(){
        changeNameHref!!.click()
    }

    fun clickChangePassword(){
        changePasswordHref!!.click()
    }

    fun inputName(name: String?) {
        changeNameInput?.sendKeys(name)
    }

    fun inputSurname(name: String?) {
        changeSunameInput?.sendKeys(name)
    }

    fun input2Name(name: String?) {
        change2NameInput?.sendKeys(name)
    }

    fun clickSaveName(){
        saveNameBtn!!.click()
    }

    fun inputOldPassword(pass: String?) {
        oldPasswordInput?.sendKeys(pass)
    }

    fun inputNewPassword1(pass: String?) {
        newPassword1Input?.sendKeys(pass)
    }

    fun inputNewPassword2(pass: String?) {
        newPassword2Input?.sendKeys(pass)
    }

    fun clickSavePassword(){
        savePasswordBtn!!.click()
    }
}