package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {


    @Test
    public void updateBookingTest() {

        //curl -X PUT \
        //  https://restful-booker.herokuapp.com/booking/1 \
        //  -H 'Content-Type: application/json' \
        //  -H 'Accept: application/json' \
        //  -H 'Cookie: token=abc123' \
        //  -d '{
        //    "firstname" : "James",
        //    "lastname" : "Brown",
        //    "totalprice" : 111,
        //    "depositpaid" : true,
        //    "bookingdates" : {
        //        "checkin" : "2018-01-01",
        //        "checkout" : "2019-01-01"
        //    },
        //    "additionalneeds" : "Breakfast"
        //}'


        //}'// curl -X POST \
        //  https://restful-booker.herokuapp.com/auth \
        //  -H 'Content-Type: application/json' \
        //  -d '{
        //    "username" : "admin",
        ////  "password" : "password123"


        // token olustur

        String token = createToken();

        // rezervasyon olustir

        Response createBookingObject = createBooking();
        int bookingId = createBookingObject.jsonPath().getJsonObject("bookingid");


        // request yap

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingObject("cagla", "topak", 200, true)) // buraya yeni degerler girerek guncelleme yapmis oluruzu
                .put("https://restful-booker.herokuapp.com/booking/" + bookingId);


        response.prettyPrint();


        // assertionlari yaz


        String firstName = response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("cagla", firstName);

        String lastName = response.jsonPath().getJsonObject("lastname");
        Assertions.assertEquals("topak", lastName);

        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals(200, totalPrice);

        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");
        Assertions.assertEquals(true, depositPaid);


    }
}

   /* public String createToken() {                    // donen sifrenin string deger dondurmesini istedigimiz icin methodun degeri strind ile  baslar.
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

*/










