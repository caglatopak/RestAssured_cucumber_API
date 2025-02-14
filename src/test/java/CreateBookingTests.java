import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests {





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


        JSONObject body = new JSONObject();  // json objesi olusturma

         body.put("firstname","cagla");
         body.put ("lastname", "topak");
         body.put ("totalprice" , 200) ;
         body.put("depositpaid", true);



         JSONObject bookingDates= new JSONObject();

         bookingDates.put("checkin", "2024-01-01");
         bookingDates.put ("checkout", "2025-01-01");


         body.put("bookingdates", bookingDates);  // ana govdeye eklendi
         body.put("additionalneeds", "evcil hayvan kabul edilen oda ");


       //cagryi gercejklestir // post cagrisi


        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body.toString())  // body burdajson objesi oldugu icin string e cevrimemiz gerekir bunuda tostring metodu ile yapariz.
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();
        response
                .then()
                .statusCode(200);

        Assertions.assertEquals("cagla", response.jsonPath().getJsonObject("booking.firstname")); // first name e erisim saglamak icin ondan onceki bagli oldugu path i yazmamiz gerekir
        Assertions.assertEquals("topak", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer)response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));
















    }























}
