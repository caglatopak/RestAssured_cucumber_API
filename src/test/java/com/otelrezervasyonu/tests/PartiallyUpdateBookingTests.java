package com.otelrezervasyonu.tests;

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
        body.put("firstname", "Ahmet");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());

        Assertions.assertEquals("Ahmet", response.jsonPath().getJsonObject("firstname"));



    }


}
