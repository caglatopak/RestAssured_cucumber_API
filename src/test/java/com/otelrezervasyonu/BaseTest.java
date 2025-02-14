package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Response createBooking() {

        Response response = given()      // rezervasyon olusturma methodunuda base test altina alirsak boylece diger siniflarda onu kullanabilir ve
                // bizim tekrar tekrar kod yazmamiza gerek kalmaz boylece projemiz daha okunakli sade ve duzenli olmus olur
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("sevinc", "ali" , 400, true)) // body burdajson objesi oldugu icin string e cevrimemiz gerekir bunuda tostring metodu ile yapariz.
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();
        response
                .then()
                .statusCode(200);
        return response;


    }



    protected String bookingObject(String firstname, String lastname, int totalPrice, boolean depositPaid) {// booking objesi parametrik bir
                                                                                       // hale getirdik parantez icine deger parametreler verdik

        JSONObject body = new JSONObject();   // olusturdugumuz objeler bir arada ve daha kolay tekrar kullanilmasi icin basetest sinifina tasindi.

        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);


        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2025-01-01");


        body.put("bookingdates", bookingDates);  // ana govdeye eklendi
        body.put("additionalneeds", "evcil hayvan kabul edilen oda ");


        return body.toString();


    }

    protected String createToken() {                    // donen sifrenin string deger dondurmesini istedigimiz icin methodun degeri strind ile  baslar.
                                                     // token uretme methodu da diger siniflarin kullanabilmesi icin base test sinifina alindi.

        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");


        Response response = given()

                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post(" https://restful-booker.herokuapp.com/auth ");


        response.prettyPrint();

        return response.jsonPath().getJsonObject("token");
    }


}
