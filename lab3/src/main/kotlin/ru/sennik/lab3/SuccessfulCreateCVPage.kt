package ru.sennik.lab3

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

/**
 * @author Natalia Nikonova
 */
class SuccessfulCreateCVPage(driver: WebDriver) {
   private var driver: WebDriver

   @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div[2]/div[1]/div[1]/h2/span")
   private val successHeader: WebElement? = null

   @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[2]/div[1]/div/div/div/div[1]/a")
   private val goToCVButton: WebElement? = null

   init {
      PageFactory.initElements(driver, this)
      this.driver = driver
   }

   fun checkSuccessHeader(): Boolean = runCatching { successHeader?.isDisplayed }.isSuccess

   fun clickGoToCVButton() {
      goToCVButton?.click()
   }
}
