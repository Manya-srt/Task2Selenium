import Pages.BlanckPage;
import Pages.InsurancePage;
import Pages.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task2Test extends BaseTest{

   @Test
   public void testInsurance() throws Exception {

      MainPage mainPage = new MainPage(driver);
      mainPage.selectMainMenu("Страхование");
      mainPage.selectSubMenu("Путешествия и покупки");

      new InsurancePage(driver).sendButton.click;

      BlanckPage blanckPage = new BlanckPage(driver);
      String actualTitle = blanckPage.title.getText();
      assertEquals("Страхование путешественников", actualTitle);

      blanckPage.fillField("insured0_surname", "Smirnova");
      blanckPage.fillField("insured0_name", "Anna");
      blanckPage.fillField("insured0_birthD0ate", "12.11.1990");




   }



}
