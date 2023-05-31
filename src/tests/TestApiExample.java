//package tests;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.Test;
//
//
//public class TestApiExample {
//
//    @Test
//    public void test() {
//
////        System.setProperty("https.proxyPort", "8080");
////        System.setProperty("https.proxyHost", "pac.gov.il");
//
////            RestAssured.baseURI = "https://dummy.restapiexample.com";
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
//                .then()
//                .extract().response();
//
//        System.out.println("The response status is " + response.getStatusCode());
//        //System.out.println(response.then().assertThat().);
//
//    }
//}
