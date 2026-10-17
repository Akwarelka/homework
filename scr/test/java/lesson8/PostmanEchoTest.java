package lesson8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostmanEchoTest {

    private static final String COMMON_BODY = "This is expected to be sent back as part of response body.";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    private void printTestResult(int stepNumber, String testName, Response response) {
        System.out.println(stepNumber + ") " + testName);
        System.out.println("Код ответа (Status Code): " + response.getStatusCode());
        System.out.println("Тело ответа (Response Body):");
        System.out.println(response.prettyPrint());
    }

    @Test
    @Order(1)
    @DisplayName("1) GET Request")
    public void test01_GetRequest() {
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .extract().response();

        printTestResult(1, "GET Request", response);
    }

    @Test
    @Order(2)
    @DisplayName("2) POST Raw Text")
    public void test02_PostRawText() {
        Response response = given()
                .contentType(ContentType.TEXT)
                .body(COMMON_BODY)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(COMMON_BODY))
                .extract().response();

        printTestResult(2, "POST Raw Text", response);
    }

    @Test
    @Order(3)
    @DisplayName("3) POST Form Data")
    public void test03_PostFormData() {
        Response response = given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .extract().response();

        printTestResult(3, "POST Form Data", response);
    }

    @Test
    @Order(4)
    @DisplayName("4) PUT Request")
    public void test04_PutRequest() {
        Response response = given()
                .contentType(ContentType.TEXT)
                .body(COMMON_BODY)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(COMMON_BODY))
                .extract().response();

        printTestResult(4, "PUT Request", response);
    }

    @Test
    @Order(5)
    @DisplayName("5) PATCH Request")
    public void test05_PatchRequest() {
        Response response = given()
                .contentType(ContentType.TEXT)
                .body(COMMON_BODY)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(COMMON_BODY))
                .extract().response();

        printTestResult(5, "PATCH Request", response);
    }

    @Test
    @Order(6)
    @DisplayName("6) DELETE Request")
    public void test06_DeleteRequest() {
        Response response = given()
                .contentType(ContentType.TEXT)
                .body(COMMON_BODY)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(COMMON_BODY))
                .extract().response();

        printTestResult(6, "DELETE Request", response);
    }
}