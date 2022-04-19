Cenários de Teste
- abrir o bowser
- Acessar 0 site ("http://www.juliodelima.com.br/taskit")
- Fazer Login
- Acessar o SigInBox
- Adiconar Usuário
- Nme, Phone, Contact, Save
- Validar "Your contact has been added"
- Remover Contato de usuário
- Validar "Rest in peace, dear phone"!


package testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import supote.Generator;
import supote.Screenshot;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
        private WebDriver navegador;

        @Rule
        public TestName test = new TestName();

        @Before
        public void informacoesusuariotest() {
        //Abrinodo O Navegador
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\100\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.manage().window().maximize();

        //Navegando para vpágina taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

            // Clicar no link que possui e texto "Sign  in"
            navegador.findElement(By.linkText("Sign in")).click();

            // Identificando o formulário de login"
            WebElement formularioSigninBox = navegador.findElement(By.id("signinbox"));

            // Digitar no campo com o nome "Login" que está dentro do formulario de id "signinbox" o texto "julio0001"
            formularioSigninBox.findElement(By.name("login")).sendKeys("32320268");

            // Digitar no campo com o nome "password" que está dentro do formulario de id "signinbox" o texto "123456"
            formularioSigninBox.findElement(By.name("password")).sendKeys("prmarco");

            // Clicar no link com o texto "SIGN IN"
            navegador.findElement(By.linkText("SIGN IN")).click();

            //Clicar em um link que possue a class "me"
            navegador.findElement(By.className("me")).click();

            //Clicar em um link que possue o texto MORE DATA ABOUT YOU"
            navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

        //@Test
        public void testAdicionarUmaInformacaoAdicionalDoUsuario() {

            //Clicar no botão através do seu xpath //button[@data-target="addmoredate"]
            navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

            //Identificar o popup onde está o formulário de id addmaredate
           WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

            //na conbo de name "type" escolher a opção "Phone"
            WebElement compoType = popupAddMoreData.findElement(By.name("type"));
            new Select(compoType).selectByVisibleText("Phone");

            //No começo do name "contact" digitar "+5585999999999"
            popupAddMoreData.findElement(By.name("contact")).sendKeys("+5585999999999");

            //Clicar no link de text "SAVE" que está no popup
            popupAddMoreData.findElement(By.linkText("SAVE")).click();

            //Na mensagem de id "toast-container" validar que o texto é "your contact has been added"
            WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
            String mensagem = mensagemPop.getText();
            assertEquals("your contact has been added", mensagem);

        }

        @Test
        public void removerUmContatoDeUmUsuario() {
            //Clicar no elemento pelo seu xpath //span[text()="+558533334444"]/following-sibling::a
            navegador.findElement(By.xpath("//span[text()=\"+558533334444\"]/following-sibling::a")).click();
            //Cconfirmar a janela javascript
            navegador.switchTo().alert().accept();

            //Validar se a mansagem apresentada foi Rest in peace, dear phone!
            WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
            String mensagem = mensagemPop.getText();
            assertEquals("Rest in peace, dear phone!", mensagem);

            String screenshotArquivo = "/Users/Marco Auréluio/test-report/taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
            Screenshot.tirar(navegador, screenshotArquivo);


            //Aguardar até 10 segundos para que a janela desapareça
            WebDriverWait aguardar = new WebDriverWait(navegador,10);
            aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

            //Clicar no link com texto "logout"
            navegador.findElement(By.linkText("Logout")).click();

        }

        @After
        public void tearDown() {
            // Fechar o navegador
            navegador.close();
        }
}

