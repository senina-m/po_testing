package ru.sennik.lab3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import ru.sennik.lab3.ConfProperties.getProperty
import ru.sennik.lab3.Currency.Companion.toCurrency
import java.util.concurrent.TimeUnit

/**
 * @author Natalia Nikonova
 */
class CVPageTest {
   companion object {
      private val driver = ChromeDriver()
      val mainPage = MainPage(driver)
      val listCVPage = ListCVPage(driver)
      val cvPage = CVPage(driver)
      val loginPage = LoginPage(driver)
      val loginWithPasswordPage = LoginWithPasswordPage(driver)

      @JvmStatic
      @BeforeAll
      fun setup() {
         //определение пути до драйвера и его настройка
         System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"))
         //окно разворачивается на полный экран
         driver.manage().window().maximize()
         //задержка на выполнение теста = 1 сек.
         driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS)
      }
   }

   @BeforeEach
   fun setupMainPage() {
      driver.get(getProperty("mainpage"))
      login(getProperty("login"), getProperty("password"))
      mainPage.clickGoToCVButton()
      listCVPage.clickCreateCVButton()
   }

   @Test
   fun createWithoutRequiredFieldFailed() {
      checkNotVisibleAdditionInfo()

      cvPage.sendCV()
      Assertions.assertTrue(cvPage.checkChoseWorkErrorMessage())
   }

   @Test
   fun createWithWorkExperienceSuccessful() {
      // что не доступны поля которые после выбора опыта открываются
      checkNotVisibleAdditionInfo()
      // проверить что поля по умолчанию добавились правильно
      checkDefaultValues()
      // выбрать чтоесть опыт работы
      cvPage.selectWithWorkExperience()

      // проверить что не доступны поля для нет опыта
      Assertions.assertTrue(cvPage.checkNotExistNoWorkExperienceFields())
      // заполнить минимум
      cvPage.fillSpecialization(getProperty("post"), getProperty("salary").toInt(), getProperty("currency").toCurrency()!!)
      cvPage.addWork(
         getProperty("startWorkMonth").toInt(),
         getProperty("startWorkYear").toInt(),
         getProperty("organization"),
         getProperty("position"),
         getProperty("charge")
      )

      cvPage.sendCV()

      // проверить что отправилось успешно
      // удалить
   }

   @Test
   fun createWithoutWorkExperienceSuccessful(){
      // что не доступны поля которые после выбора опыта открываются
      checkNotVisibleAdditionInfo()
      // проверить что поля по умолчанию добавились правильно
      checkDefaultValues()

      // выбрать что нет опыт работы
      cvPage.selectWithoutWorkExperience()

      // проверить что не доступны поля для есть опыт
      Assertions.assertTrue(cvPage.checkNotExistWorkExperienceFields())
      // заполнить минимум
      cvPage.fillSpecialization(getProperty("other_post"), getProperty("salary").toInt(), getProperty("currency").toCurrency()!!)
      cvPage.sendCV()

      // проверить что отправилось успешно
      // удалить
   }

   private fun login(login: String, password: String){
      mainPage.clickLoginBtn()
      loginPage.clickLoginWithPassword()
      loginWithPasswordPage.inputLogin(login)
      loginWithPasswordPage.inputPasswd(password)
      loginWithPasswordPage.login()
   }

   private fun checkNotVisibleAdditionInfo() {
      Assertions.assertTrue(cvPage.checkNotExistEducationFields())
      Assertions.assertTrue(cvPage.checkNotExistSpecialtyFields())
      Assertions.assertTrue(cvPage.checkNotExistsLanguagesFields())
      Assertions.assertTrue(cvPage.checkNotExistAddWorkFields())
      Assertions.assertTrue(cvPage.checkNotExistNoWorkExperienceFields())
      Assertions.assertTrue(cvPage.checkNotExistWorkExperienceFields())
   }

   private fun checkDefaultValues() {
      Assertions.assertEquals(cvPage.getNameFieldText(), getProperty("name"))
      Assertions.assertEquals(cvPage.getSurnameFieldText(), getProperty("surname"))
      // Assertions.assertEquals(cvPage.getCityFieldText(), getProperty("city"))
      Assertions.assertEquals(cvPage.getSex().name, getProperty("sex"))
   }
}