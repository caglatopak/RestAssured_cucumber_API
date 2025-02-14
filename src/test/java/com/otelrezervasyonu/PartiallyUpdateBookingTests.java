package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest{

    @Test

    public void partiallyUpdateBookingTest(){

        // token olustur

      // tring token = createToken();

        // rezervasyon yap


       //esponse newBooking= createBooking();
      //int bookingId= newBooking.jsonPath().getJsonObject("bookingid");

        // int bookingId= createBookingId();


        // cagri yap


        JSONObject body = new JSONObject();
        body.put("firstname", "fadime ");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" +createToken())
                .body(body.toString())
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/" + createBookingId());

        response.prettyPrint();


        // assertionlar yap


        Assertions.assertEquals("fadime", response.jsonPath().getJsonObject("firstname"));



    }


}
