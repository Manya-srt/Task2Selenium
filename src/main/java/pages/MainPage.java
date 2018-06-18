package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    WebDriver driver;
    @FindBy(xpath = "//ul[@class='lg-menu__list']")

    WebElement mainMenu;

    @FindBy(xpath = "(//div[@class='lg-menu__sub'])[6]")
    WebElement subMenu;

       public MainPage(WebDriver driver){
           PageFactory.initElements(driver,this);
           this.driver= driver;
       }

       public void selectMainMenu(String menuItem){
           mainMenu.findElement(By.xpath(".//span[contains(text(),'"+menuItem+"')]")).click();
       }

       public void selectSubMenu(String menuItem) {
           subMenu.findElement(By.xpath(".//a[contains(text(),'" + menuItem + "')]")).click();
       }
}
