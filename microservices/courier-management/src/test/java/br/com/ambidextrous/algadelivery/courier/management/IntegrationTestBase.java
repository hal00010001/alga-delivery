package br.com.ambidextrous.algadelivery.courier.management;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CourierManagementApplication.class
)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @LocalServerPort
    protected int port;

    protected RequestSpecification given(){
        return RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

}
