package com.otelrezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBookingTest(){

        // token olustur
        // rezervasyon olustur
        // delete request yap

        Response response= given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" +createToken())
                .when()
                .delete(" /booking/"+ createBookingId());


        // assertion yap

  response
          .then()
          .statusCode(201);


    }


}
