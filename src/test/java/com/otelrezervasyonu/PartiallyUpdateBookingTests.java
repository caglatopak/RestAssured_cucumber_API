package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest{

    @Test

    public void partiallyUpdateBookingTest(){

        // token olustur

        String token = createToken();

        // rezervasyon yap


        Response newBooking= createBooking();
        int bookingId= newBooking.jsonPath().getJsonObject("bookingid");


        // cagri yap


        JSONObject body = new JSONObject();
        body.put("firstname", "fadime ");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(body.toString())
                .patch("https://restful-booker.herokuapp.com/booking/"+ bookingId);


        response.prettyPrint();


        // assertionlar yap















    }























}
