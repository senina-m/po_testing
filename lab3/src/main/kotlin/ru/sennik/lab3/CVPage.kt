package ru.sennik.lab3

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Select

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

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[3]/div[2]/div/fieldset/input")
    private val mobileField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[2]/div[2]/div[1]/div[3]/div[2]/div/div/fieldset/input")
    private val cityField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div/div/div[1]/fieldset/input")
    private val dayBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[1]/div/div[2]/div/select")
    private val monthBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[1]/div/div[2]/div[1]/div/div[3]/fieldset/input")
    private val yearBirthdayField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[3]/div/div[2]/div[1]/label/input")
    private val maleRation: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[3]/div/div[2]/div[2]/label/input")
    private val femaleRation: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[1]/div[5]/div[2]/div[1]/div/fieldset/input")
    private val citizenshipField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[4]/div/div[1]/label/input")
    private val hasWorkExperienceRatio: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[4]/div/div[2]/label/input")
    private val noWorkExperienceRatio: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[3]/div[2]/div[1]/div[4]/div/div[3]")
    private val choseWorkErrorMessage: WebElement? = null

    // have work experience

    // работает только если одно место работы добавить, потом уже xpath другой
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[5]/div[2]/div[1]/div/div[2]/div/button")
    private var addFirstlyWorkplaceButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[6]/div[1]/div[2]/div/div/span/textarea")
    private val aboutMeField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[2]/div[1]/fieldset/input")
    private val skillsField: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[2]/div[2]/button")
    private val addSkillButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[7]/div/div[2]/div/div/div[1]/div/div[2]/span")
    private val skillSecondSelectedField: WebElement? = null

    // add work

    private val monthWorkStartSelect: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[1]/div[2]/div/div/div[1]/div/div[1]/div/select"))

    private val yearWorkStartField: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/fieldset/input"))

    private val continueWorkCheckbox: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[2]/div[2]/div/div/label/input"))

    private val monthWorkEndSelect: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/select"))

    private val yearWorkEndField: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[1]/div[2]/div[1]/div/div[1]/div/div[2]/fieldset/input"))

    private val organizationField: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[3]/div/div[3]/div[1]/div/div/fieldset/input"))

    private val positionField: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[3]/div/div[6]/div[1]/div/div/fieldset/input"))

    private val chargeField: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div[1]/div/form/div/div[4]/div[2]/div/div/textarea"))

    private val saveWorkplace: WebElement?
        get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[4]/div[2]/button"))

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

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    fun sendCV() {
        var i = 4
        while (runCatching {
            driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/form/div[$i]/div/button")).click()
        }.isFailure) {
            i++
        }
    }

    fun checkChoseWorkErrorMessage(): Boolean = runCatching { choseWorkErrorMessage?.isDisplayed != true }.isSuccess

    fun checkNotExistWorkExperienceFields(): Boolean = runCatching {
        addFirstlyWorkplaceButton?.isDisplayed != true
                && aboutMeField?.isDisplayed != true
                && skillsField?.isDisplayed != true
                && addSkillButton?.isDisplayed != true
                && skillSecondSelectedField?.isDisplayed != true
    }.isFailure

    fun checkNotExistAddWorkFields(): Boolean = runCatching {
        monthWorkStartSelect?.isDisplayed != true
            && yearWorkStartField?.isDisplayed != true
            && continueWorkCheckbox?.isDisplayed != true
            && organizationField?.isDisplayed != true
            && positionField?.isDisplayed != true
            && chargeField?.isDisplayed != true
    }.isFailure

    fun checkNotExistEndWorkFields(): Boolean = runCatching {
        monthWorkEndSelect?.isDisplayed != true
            && yearWorkEndField?.isDisplayed != true
    }.isFailure

    fun checkNotExistNoWorkExperienceFields(): Boolean = runCatching {
        addEducationReasonField?.isDisplayed != true
            && reasonsField?.isDisplayed != true
    }.isFailure

    fun checkNotExistSpecialtyFields(): Boolean = runCatching {
        postField?.isDisplayed != true
                && valueSalaryField?.isDisplayed != true
                && currencySalarySelect?.isDisplayed != true
    }.isFailure

    fun checkNotExistEducationFields(): Boolean = runCatching {
            degreeSelect?.isDisplayed != true
                    && universityField?.isDisplayed != true
                    && departmentField?.isDisplayed != true
                    && specializationField?.isDisplayed != true
                    && endYearField?.isDisplayed != true
        }.isFailure

    fun checkNotExistLanguagesFields(): Boolean = runCatching {
        nativeLanguageField?.isDisplayed != true
            && addOtherLanguageField?.isDisplayed != true
    }.isFailure


    fun checkNotExistsLanguagesFields(): Boolean = runCatching {
        firstOtherLanguageNameField?.isDisplayed != true
            && firstOtherLanguageLevelSelect?.isDisplayed != true
    }.isFailure

    fun getNameFieldText(): String? = nameField?.getAttribute("value")

    fun getSurnameFieldText(): String? = surnameField?.getAttribute("value")

    fun getCityFieldText(): String? = cityField?.getAttribute("value")

    fun getSex(): Sex {
        return if (maleRation?.isSelected == true) Sex.MALE
        else Sex.FEMALE
    }

    fun selectWithWorkExperience() {
        (driver as JavascriptExecutor).executeScript("arguments[0].click();", hasWorkExperienceRatio)
    }

    fun selectWithoutWorkExperience() {
        (driver as JavascriptExecutor).executeScript("arguments[0].click();", noWorkExperienceRatio)
    }

    fun fillSpecialization(postName: String, salary: Int, currency: Currency) {
        repeat(30) { postField?.sendKeys(Keys.BACK_SPACE) }
        postField?.sendKeys(postName)
        valueSalaryField?.sendKeys(salary.toString())
        val currencySelector = Select(currencySalarySelect)
        currencySelector.selectByIndex(currency.ordinal)
    }

    fun addWork(startMonth: Int, startYear: Int, organization: String, position: String, charge: String) {
        addFirstlyWorkplaceButton?.click()
        val monthSelect = Select(monthWorkStartSelect)
        monthSelect.selectByIndex(startMonth)
        yearWorkStartField?.sendKeys(startYear.toString())
        organizationField?.sendKeys(organization)
        positionField?.sendKeys(position)
        chargeField?.sendKeys(charge)
        saveWorkplace?.click()
    }
}

enum class Sex {
    MALE,
    FEMALE
}

enum class Currency {
    RUB,
    EUR,
    USD;

    companion object {
        private val valueMap = Currency.values().associateBy { it.name }

        fun String.toCurrency() = valueMap[this]
    }
}
