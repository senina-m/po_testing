package ru.sennik.lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


class SearchPage(driver: WebDriver) {
    private var driver: WebDriver

    // заголовок поиска
    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div[1]/h1")
    private val searchHeader: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div[2]/div/div[2]/div[2]")
    private val cvs: WebElement? = null

    @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div[2]/div/div[2]/div[3]")
    private val companies: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
        this.driver = driver
    }

    val getVacancyHeader: String
        get() = searchHeader!!.text

    fun getVacanciesHeaders(num: Int): Array<String?>{
        val result = Array<String?>(num)  { null }
        for (i: Int in 0 until num) {
            result[i] = driver.findElement(
                By.xpath("/html/body/div[5]/div/div[3]/div[1]/div/div[2]/div[2]/div[2]/main/div[1]/div[${3+i}]/div/div[1]/div/div[1]/h3/span/a"))?.text
            //*[@id="a11y-main-content"]/div[4]/div/div[1]/div[1]/div[3]/h3/span/a
            //*[@id="a11y-main-content"]/div[4]/div/div[1]/div/div[3]/h3
//            /html/body/div[5]/div/div[3]/div[1]/div/div[2]/div[2]/div[2]/main/div[1]/div[3]/div/div[1]/div/div[1]/h3/span/a
//            /html/body/div[5]/div/div[3]/div[1]/div/div[2]/div[2]/div[2]/main/div[1]/div[5]/div/div[1]/div/div[3]/h3/span/a
//            /html/body/div[5]/div/div[3]/div[1]/div/div[2]/div[2]/div[2]/main/div[1]/div[6]/div/div[1]/div/div[1]/h3/span/a
        }
        return result
    }

    fun getCVHeaders(num: Int): Array<String?>{
        val result = Array<String?>(num)  { null }
        for (i: Int in 0 until num) {
            result[i] = driver.findElement(
                By.xpath("//*[@id=\"a11y-main-content\"]/div[${1 + i}]/div[2]/div[1]/div[1]/div[1]/span/a"))?.text
        }
        return result
    }

    fun getCompaniesHeaders(num: Int): Array<String?>{
        val result = Array<String?>(num)  { null }
        for (i: Int in 0 until num) {
            result[i] = driver.findElement(
                By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div[1]/div/table/tbody/tr/td/div[${1 + i}]/a"))?.text
        }
        return result
    }

    fun searchCVs(){
        cvs?.click()
    }
    fun searchCompanies(){
        companies?.click()
    }
}