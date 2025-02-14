import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests {

    // cagriyi olustur (get)
    // cagridan gelen response kontrol et
    // https://restful-booker.herokuapp.com/booking

    @Test

    public void getAllBookingTest() {

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all()  // bu method ile konsola cikti gorulur yaptigimiz cagrinin sonucunu gosterir.sonrasinda oteldeki rezervasyonlar listelendi
                .statusCode(200);  // cagrinin sonucunun dondurdugu kod

    }
}