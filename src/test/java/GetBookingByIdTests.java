import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests {

    @Test
     public void getBookingById(){

          // cagriyi olusturmami
         // responce kontrolleri
         // curl -i https://restful-booker.herokuapp.com/booking/1


        Response response = given()
             .when()
             .get("https://restful-booker.herokuapp.com/booking/1242");

            // .then()
            // .log().all()
           //  .statusCode(200);

        response
                .then()
                .statusCode(200);

         response.prettyPrint();

         String firstname = response.jsonPath().getJsonObject("firstname"); // response icindeki jsonpath den bir objeye ersiim saglandi
         String lastname  = response.jsonPath().getJsonObject("lastname");
         int totalprice  = response.jsonPath().getJsonObject("totalprice");
         Boolean depositpaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Josh", firstname); // dogrulama beklenne sonuc ile gerceklesen sonucu karsilastirmak
        // icin kullanilr/api nin verdigi yanitlarin dogru olup olmadigini kontrol ederiz

        Assertions.assertEquals("Allen", lastname);

        Assertions.assertEquals(111,totalprice);

        Assertions.assertEquals(true,depositpaid);


     }



}
