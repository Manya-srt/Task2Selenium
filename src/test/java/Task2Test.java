import Pages.BlanckPage1;
import Pages.BlanckPage2;
import Pages.InsurancePage;
import Pages.MainPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task2Test extends BaseTest{

   @Test
   public void testInsurance() {
       // Нажать на – Страхование
       // Выбрать – Путешествия и покупки
       driver.get(baseUrl);
       MainPage mainPage = new MainPage(driver);
       mainPage.selectMainMenu("Страхование");
       mainPage.selectSubMenu("Путешествия и покупки");

       // Проверить наличие на странице заголовка – Страхование путешественников
       InsurancePage insurancePage = new InsurancePage(driver);
       assertEquals("Страхование путешественников", insurancePage.title.getText());

       // Нажать на – Оформить Онлайн
       new InsurancePage(driver).issueButtonClick.click();

       //Переключение экрана
       for (String handle : driver.getWindowHandles()){
           driver.switchTo().window(handle);
       }

       // На вкладке – Выбор полиса  выбрать сумму страховой защиты – Минимальная
       BlanckPage1 blanckPage1 = new BlanckPage1(driver);
       blanckPage1.selectSum("Минимальная");

       // Нажать Оформить
       blanckPage1.issueButton.click();

       // На вкладке Оформление заполнить поля:
       BlanckPage2 blanckPage2 = new BlanckPage2(driver);
       String actualTitle = blanckPage2.blanck_name.getText();
       String expectedTitle = "Оформление";
       assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
               actualTitle, expectedTitle), actualTitle.contains(expectedTitle));
       
      // Фамилию и Имя, Дату рождения застрахованных
       blanckPage2.fillField("Застрахованные: Фамилия", "Smirnova");
       blanckPage2.fillField("Застрахованные: Имя", "Anna");
       blanckPage2.fillField("Застрахованные: Дата рождения", "12.11.1990");

       // Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
       blanckPage2.fillField("Фамилия", "Смирнова");
       blanckPage2.fillField("Имя", "Олеся");
       blanckPage2.fillField("Отчество", "Петровна");
       blanckPage2.birthDate.click();
       blanckPage2.fillField("Дата рождения", "13.05.1987");
       blanckPage2.female.click();

       // Паспортные данные
       blanckPage2.fillField("Серия паспорта", "5204");
       blanckPage2.fillField("Номер паспорта", "424578");
       blanckPage2.fillField("Дата выдачи", "02.06.2014");
       blanckPage2.fillField("Место выдачи", "ОУФМС России по г. Новосибирск");

       // Контактные данные не заполняем

       //Проверить, что все поля заполнены правильно
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

       //Нажать кнопку Продолжить
       blanckPage2.continueButton.click();

       // Проверить, что появилось сообщение - Заполнены не все обязательные поля
       WebElement webElement = driver.findElement(By.xpath("//div[@ng-show='tryNext && myForm.$invalid']"));
       assertEquals("Заполнены не все обязательные поля", webElement.getText());
   }
}
