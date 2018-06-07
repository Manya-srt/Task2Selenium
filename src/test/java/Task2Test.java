import Pages.BlanckPage1;
import Pages.BlanckPage2;
import Pages.InsurancePage;
import Pages.MainPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class Task2Test extends BaseTest{

   @Test
   public void testInsurance() {
       driver.get(baseUrl);
       MainPage mainPage = new MainPage(driver);
       mainPage.selectMainMenu("Страхование");
       mainPage.selectSubMenu("Путешествия и покупки");

       InsurancePage insurancePage = new InsurancePage(driver);
       Assert.assertEquals("Страхование путешественников", insurancePage.title.getText());

       new InsurancePage(driver).issueButtonClick.click();

       for (String handle : driver.getWindowHandles()){ //Переключение экрана
           driver.switchTo().window(handle);
       }

       BlanckPage1 blanckPage1 = new BlanckPage1(driver);
       blanckPage1.selectSum("Минимальная");
       blanckPage1.issueButton.click();

       BlanckPage2 blanckPage2 = new BlanckPage2(driver);
       String actualTitle = blanckPage2.blanck_name.getText();
       String expectedTitle = "Оформление";
       assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
               actualTitle, expectedTitle), actualTitle.contains(expectedTitle));

       blanckPage2.fillField("Застрахованные: Фамилия", "Smirnova");
       blanckPage2.fillField("Застрахованные: Имя", "Anna");
       blanckPage2.fillField("Застрахованные: Дата рождения", "12.11.1990");

       blanckPage2.fillField("Фамилия", "Смирнова");
       blanckPage2.fillField("Имя", "Олеся");
       blanckPage2.fillField("Отчество", "Петровна");
       blanckPage2.birthDate.click();
       blanckPage2.fillField("Дата рождения", "13.05.1987");
       blanckPage2.female.click();

       blanckPage2.fillField("Серия паспорта", "5204");
       blanckPage2.fillField("Номер паспорта", "424578");
       blanckPage2.fillField("Дата выдачи", "02.06.2014");
       blanckPage2.fillField("Место выдачи", "ОУФМС России по г. Новосибирск");

       blanckPage2.checkFieldData("Застрахованные: Фамилия","Smirnova");
       blanckPage2.checkFieldData("Застрахованные: Имя", "Anna");
       blanckPage2.checkFieldData("Застрахованные: Дата рождения","12.11.1990");

       blanckPage2.checkFieldData("Фамилия","Смирнова");
       blanckPage2.checkFieldData("Имя","Олеся");
       blanckPage2.checkFieldData("Отчество", "Петровна");
       blanckPage2.checkFieldData("Дата рождения","13.05.1987");

       blanckPage2.checkFieldData("Серия паспорта","5204");
       blanckPage2.checkFieldData("Номер паспорта","424578");
       blanckPage2.checkFieldData("Дата выдачи", "02.06.2014");
       blanckPage2.checkFieldData("Место выдачи","ОУФМС России по г. Новосибирск");

       blanckPage2.continueButton.click();

       By by = By.xpath("//div[@ng-show='tryNext && myForm.$invalid']");
       Assert.assertEquals(true, isElementPresent(by));
   }
}
