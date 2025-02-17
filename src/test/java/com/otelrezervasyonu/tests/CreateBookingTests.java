package com.otelrezervasyonu.tests;

import com.otelrezervasyonu.models.Booking;
import com.otelrezervasyonu.models.BookingDates;
import com.otelrezervasyonu.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest{ // yani bu sinif basetest sinifinin bir alt sinifi oluyor


    @Test

    public void createBookingTest(){

        // body olusturmamiz gerekir

        //curl -X POST \
        //  https://restful-booker.herokuapp.com/booking \
        //  -H 'Content-Type: application/json' \
        //  -d '{
        //    "firstname" : "Jim",
        //    "lastname" : "Brown",
        //    "totalprice" : 111,
        //    "depositpaid" : true,
        //    "bookingdates" : {  /// kendi basina bir json objesi oldugu icin ayrica tanimlanmasi gerekir
        //        "checkin" : "2018-01-01",
        //        "checkout" : "2019-01-01"
        //    },
        //    "additionalneeds" : "Breakfast"
        //}'


       /* JSONObject body = new JSONObject();  // json objesi olusturma // basetest sinifinda daha duzenli gorunmesi icin basetest sinifina tasindi

         body.put("firstname","cagla");
         body.put ("lastname", "topak");
         body.put ("totalprice" , 200) ;
         body.put("depositpaid", true);



         JSONObject bookingDates= new JSONObject();

         bookingDates.put("checkin", "2024-01-01");
         bookingDates.put ("checkout", "2025-01-01");


         body.put("bookingdates", bookingDates);  // ana govdeye eklendi
         body.put("additionalneeds", "evcil hayvan kabul edilen oda ");

*/
       //cagryi gercejklestir // post cagrisi


       /* Response response = given()   // rezervasyon olusturma methodunuda base test altina alirsak boylece diger siniflarda onu kullanabilir ve
                                      // bizim tekrar tekrar kod yazmamiza gerek kalmaz boylece projemiz daha okunakli sade ve duzenli olmus olur
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject() ) // body burdajson objesi oldugu icin string e cevrimemiz gerekir bunuda tostring metodu ile yapariz.
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();
        response
                .then()
                .statusCode(200);
*/
        Response response = createBooking(); // basetest sinifina tasidimiz methodu
                                             // response methoduna tasidiktan sonra burda methodu cagirrarak assertionlarimiz yapabiliriz


        Assertions.assertEquals("Ozan", response.jsonPath().getJsonObject("booking.firstname")); // first name e erisim saglamak icin ondan onceki bagli oldugu path i yazmamiz gerekir
        Assertions.assertEquals("Ilhan", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer)response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));


    }


    @Test
    public void createBookingWithPojo() {
        BookingDates bookingDates = new BookingDates("2023-01-01", "2023-01-02");
        Booking booking = new Booking("Udemy", "Kurs", 350, true, bookingDates, "Tek kisilik yatak");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);

        Assertions.assertEquals("Udemy", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Kurs", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Tek kisilik yatak", bookingResponse.getBooking().getAdditionalneeds());
    }

}
