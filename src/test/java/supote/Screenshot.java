package supote;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Screenshot {
    public static void tirar(WebDriver navegador, String arquivo) {
        File Screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(Screenshot, new File(arquivo));
        } catch (Exception e){
            System.out.println("Houverem problemas ao copiar o arquivo para a pasta: "+ e.getMessage());
        }
    }
}
