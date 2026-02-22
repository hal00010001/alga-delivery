package br.com.ambidextrous.algadelivery.courier.management.api.controller;

import br.com.ambidextrous.algadelivery.courier.management.IntegrationTestBase;
import br.com.ambidextrous.algadelivery.courier.management.domain.model.Courier;
import br.com.ambidextrous.algadelivery.courier.management.domain.repository.CourierRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourierControllerTest extends IntegrationTestBase {

    @LocalServerPort
    private int port;

    @Autowired
    private CourierRepository courierRepository;

    private static final String BASE_PATH = "/api/v1/couriers";

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/couriers";
    }

//    Precisa ter as chaves dentro do texto para ser considerado JSON
    @Test
    void shouldReturn201(){
        String requestBody = """ 
                    {
                        "name": "João da Silva",
                        "phone": "1195500050"
                    }
                """;

        RestAssured
                .given()
                    .body(requestBody)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("id", Matchers.notNullValue())
                    .body("name", Matchers.equalTo("João da Silva"));

    }

    @Test
    void shouldReturn200(){
        UUID courierId = courierRepository.saveAndFlush(
                Courier.brandNew(
                        "Maria Souza",
                        "11912341234"
                )
        ).getId();

        System.out.println(courierId);

        assertNotNull(courierRepository);

        assertTrue(port > 0);
        System.out.println("PORT: " + port);

        RestAssured
                .given()
                    .pathParam("courierId", courierId.toString())
                    .accept(ContentType.JSON)
                .when()
                    .get("/{courierId}")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", Matchers.equalTo(courierId.toString()))
                    .body("name", Matchers.equalTo("Maria Souza"))
                    .body("phone", Matchers.equalTo("11912341234"));
    }

/*    @Test
    void shouldReturn200() {

        Courier saved = courierRepository.saveAndFlush(
                Courier.brandNew("Maria Souza", "11912341234")
        );

        UUID courierId = saved.getId();

        given()
                .when()
                .get(BASE_PATH + "/" + courierId.toString())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.equalTo(courierId.toString()))
                .body("name", Matchers.equalTo("Maria Souza"))
                .body("phone", Matchers.equalTo("11912341234"));
    }*/

/*    @Test
    void debugCall() {

        Courier saved = courierRepository.saveAndFlush(
                Courier.brandNew("Maria Souza", "11912341234")
        );

        String url = "http://localhost:" + port + "/api/v1/couriers/" + saved.getId();

        System.out.println("Calling URL: " + url);

        RestAssured
                .given()
                .when()
                .get(url)
                .then()
                .log().all();
    }*/

}