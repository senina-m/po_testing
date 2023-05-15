package ru.sennik.lab3

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

/**
 * @author Natalia Nikonova
 */
class ListCVPage(driver: WebDriver) {
   private var driver: WebDriver

   @FindBy(xpath = "//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div/div[2]/a")
   private val createCVButton: WebElement? = null

   init {
      PageFactory.initElements(driver, this)
      this.driver = driver
   }

   fun clickCreateCVButton(){
      createCVButton!!.click()
   }

   fun openCVByName(name: String) {
      var i = 0
      do {
         i++
         val currentName = driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div[1]/div[5]/div[$i]/div/h3/a/span"))
      } while (currentName.text != name)
      driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div[1]/div[5]/div[$i]/div/h3/a")).click()
   }
}
