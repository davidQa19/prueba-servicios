package co.com.sofka.stepDefinition;

import co.com.sofka.setup.servicioSetUp;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PingStepDefinition extends servicioSetUp {

    private static final Logger LOGGER = Logger.getLogger(PingStepDefinition.class);

    private Response response;

    private RequestSpecification request;
    @Dado("dado que el admininistrador se encuentra validando el servicio ping")
    public void dadoQueElAdmininistradorSeEncuentraValidandoElServicioPing() {
        try{
            generalSetUp();
            request = given()
                    .log()
                    .all();
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Cuando("realiza la peticion get")
    public void realizaLaPeticionGet() {
        try{
            response = request.when()
                    .get(BASE_ping);
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Entonces("recibe un codigo exitoso y el mensaje Created")
    public void recibeUnCodigoExitosoYElMensajeCreated() {
        response.then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CREATED)
                .body(equalTo("Created"));
    }
}
