package ru.sennik.lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

/**
 * @author Natalia Nikonova
 */
class CVViewPage(driver: WebDriver) {
   private var driver: WebDriver

   init {
      PageFactory.initElements(driver, this)
      this.driver = driver
   }

   @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[2]/div/div/div/button[3]")
   private val deleteButton: WebElement? = null

   private val confirmDeleteButton: WebElement?
      get(): WebElement? = driver.findElement(By.xpath("/html/body/div[12]/div/div[1]/div[2]/div/div[1]/form/button"))

   fun clickDeleteButton() {
      deleteButton?.click()
   }

   fun clickConfirmDeleteButton() {
      confirmDeleteButton?.click()
   }
}
