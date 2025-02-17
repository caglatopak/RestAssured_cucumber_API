package com.otelrezervasyonu.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest{

    // cagriyi olustur (get)
    // cagridan gelen response kontrol et
    // https://restful-booker.herokuapp.com/booking

    @Test

    public void getAllBookingTest() {

        given(spec)
                .when()
                .get("/booking")
                .then()
                //.log().all()  // bu method ile konsola cikti gorulur yaptigimiz cagrinin sonucunu gosterir.sonrasinda oteldeki rezervasyonlar listelendi
                .statusCode(200);  // cagrinin sonucunun dondurdugu kod


    }

      @Test
      public void getBookings_with_firstname_filter_test(){

        // yeni bir rezercasyon olustur

         int bookingId=  createBookingId();


          // cagrmiza query parametresi ekle

          spec.queryParam("firstname", "Ozan");
          spec.queryParam("lastname","Ilhan");


          // cagriyi gercejlestire.
          Response response = given(spec)
                  .when()
                  .get("/booking");

          // aseerion yaz

          response
                  .then()
                  .statusCode(200);
       List<Integer> filtrelenenRezervasyon = response.jsonPath().getList("bookingid");

        //  System.out.println(list);

          Assertions.assertTrue(filtrelenenRezervasyon.contains(bookingId));


      }


}