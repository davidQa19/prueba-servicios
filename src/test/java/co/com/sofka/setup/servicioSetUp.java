package co.com.sofka.setup;

import io.restassured.RestAssured;
import org.apache.log4j.PropertyConfigurator;
import static io.restassured.RestAssured.baseURI;


import java.security.Provider;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class servicioSetUp {
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    protected static final String BASE_recursotoken = "/auth";
    protected static final String BASE_ping = "/ping";


    protected void generalSetUp (){
        setUplog4jValues();
        setUpBases();

    }

    private void setUplog4jValues() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }


    private void setUpBases(){
        baseURI = BASE_URI;
    }



    



}
