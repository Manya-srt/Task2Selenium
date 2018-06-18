import pages.ProgramChoicePage;
import pages.BlanckPage;
import pages.InsurancePage;
import pages.MainPage;
import org.junit.Test;

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
       ProgramChoicePage programChoicePage = new ProgramChoicePage(driver);
       programChoicePage.selectSum("Минимальная");

       // Нажать Оформить
       programChoicePage.issueButton.click();

       // На вкладке Оформление заполнить поля:
       BlanckPage blanckPage = new BlanckPage(driver);
       String actualTitle = blanckPage.blanck_name.getText();
       String expectedTitle = "Оформление";
       assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
               actualTitle, expectedTitle), actualTitle.contains(expectedTitle));

      // Фамилию и Имя, Дату рождения застрахованных
       blanckPage.fillField("Застрахованные: Фамилия", "Smirnova");
       blanckPage.fillField("Застрахованные: Имя", "Anna");
       blanckPage.fillField("Застрахованные: Дата рождения", "12.11.1990");


       // Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол
       blanckPage.fillField("Фамилия", "Смирнова");
       blanckPage.fillField("Имя", "Олеся");
       blanckPage.fillField("Отчество", "Петровна");
       blanckPage.birthDate.click();
       blanckPage.fillField("Дата рождения", "13.05.1987");
       blanckPage.female.click();

       // Паспортные данные
       blanckPage.fillField("Серия паспорта", "5204");
       blanckPage.fillField("Номер паспорта", "424578");
       blanckPage.fillField("Дата выдачи", "02.06.2014");
       blanckPage.fillField("Место выдачи", "ОУФМС России по г. Новосибирск");

       // Контактные данные не заполняем

       //Проверить, что все поля заполнены правильно
       blanckPage.checkFieldData("Застрахованные: Фамилия","Smirnova");
       blanckPage.checkFieldData("Застрахованные: Имя", "Anna");
       blanckPage.checkFieldData("Застрахованные: Дата рождения","12.11.1990");

       blanckPage.checkFieldData("Фамилия","Смирнова");
       blanckPage.checkFieldData("Имя","Олеся");
       blanckPage.checkFieldData("Отчество", "Петровна");
       blanckPage.checkFieldData("Дата рождения","13.05.1987");

       blanckPage.checkFieldData("Серия паспорта","5204");
       blanckPage.checkFieldData("Номер паспорта","424578");
       blanckPage.checkFieldData("Дата выдачи", "02.06.2014");
       blanckPage.checkFieldData("Место выдачи","ОУФМС России по г. Новосибирск");

       //Нажать кнопку Продолжить
       blanckPage.continueButton.click();

       // Проверить, что появилось сообщение - Заполнены не все обязательные поля
       assertEquals("Заполнены не все обязательные поля", blanckPage.errorMessage.getText());
   }
}
