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

    @FindBy(xpath = "//ul[contains(@class,'lg-menu__list')]//li[6]//div[@class='kitt-row']//div[1]//ul")
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
