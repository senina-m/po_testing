package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

/**
 * @author Natalia Nikonova
 */
class CVPage(driver: WebDriver) {
    private var driver: WebDriver

    // main info

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div/fieldset/input")
    private val nameField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div/div/fieldset/input")
    private val surnameField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/fieldset/input")
    private val emailField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[3]/div[2]/div/fieldset/input")
    private val mobileField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[5]/div[2]/div/div/fieldset/input")
    private val cityField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div/div/div[1]/fieldset/input")
    private val dayBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[1]/div/div[2]/div/select")
    private val monthBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[1]/div/div[3]/fieldset/input")
    private val yearBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[3]/div/div[2]/div[1]/label/input")
    private val maleField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[3]/div/div[2]/div[2]/label/input")
    private val femaleField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[5]/div[2]/div[1]/div/fieldset/input")
    private val citizenshipField: WebElement? = null

    // have work experience

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[2]/div/button")
    private val addWorkplaceButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[6]/div[1]/div[2]/div/div/span/textarea")
    private val aboutMeField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[2]/div[1]/fieldset/input")
    private val skillsField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[2]/div[2]/button")
    private val addSkillButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[1]/div/div[2]/span")
    private val skillSecondSelectedField: WebElement? = null

    // haven't got work experience

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[4]/div[2]/div/div[2]/span[2]/button")
    private val addEducationReasonField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[4]/div[2]/div/span/textarea")
    private val reasonsField: WebElement? = null

    // specialty

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[4]/div[2]/div[1]/div[1]/div[2]/div/fieldset/input")
    private val postField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[4]/div[2]/div[1]/div[3]/div[2]/div[1]/fieldset/input")
    private val valueSalaryField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[4]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/select")
    private val currencySalarySelect: WebElement? = null

    // education degree

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[1]/div[2]/div/select")
    private val degreeSelect: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/div/fieldset/input")
    private val universityField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div/fieldset/input")
    private val departmentField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[3]/div[1]/div[3]/div[2]/div/div/fieldset/input")
    private val specializationField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[3]/div[1]/div[4]/div[2]/div[1]/div/div/fieldset/input")
    private val endYearField: WebElement? = null

    // languages

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[10]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/span")
    private val nativeLanguageField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[10]/div[2]/div[1]/div[3]/div[2]/div/div/button")
    private val addOtherLanguageField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[10]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div[1]/div/div/div/div/span")
    private val firstOtherLanguageNameField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[10]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div[2]/div/select")
    private val firstOtherLanguageLevelSelect: WebElement? = null

    // other info

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[11]/div[2]/div/div[1]/div[1]/div[2]/div/fieldset/input")
    private val metroField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[4]/div[2]/button")
    private val sendButton: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }
}
