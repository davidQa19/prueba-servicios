package co.com.sofka.stepDefinition;

import co.com.sofka.setup.servicioSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class loginStepDefinition extends servicioSetUp {
    private static final Logger LOGGER = Logger.getLogger(loginStepDefinition.class);

   private Response response;

    private RequestSpecification request;
    private final File file = new File(System.getProperty("user.dir")+"/src/test/resources/files/credenciales.json");

    @Dado("el usuario esta en la pagina de inicio de sesion con el de usuario {string} y contraseña {string}")
    public void elUsuarioEstaEnLaPaginaDeInicioDeSesionConElDeUsuarioYContrasenia(String user, String password) {
        try{
            generalSetUp();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(file)
                    ;
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Cuando("el usuario ingrese la peticion que desea")
    public void elUsuarioIngreseLaPeticionQueDesea() {
        try{
            response = request.when()
                    .post(BASE_recursotoken);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Entonces("se mostrara un codiogo y una respuesta  exitosa y un token")
    public void seMostraraUnCodiogoYUnaRespuestaExitosaYUnToken() {
        response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
    }

}
