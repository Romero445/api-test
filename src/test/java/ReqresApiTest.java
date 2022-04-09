import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;


public class ReqresApiTest {

    @Test
    void listUserCheckTotal() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("total", is(12))
                .statusCode(200);
    }

    @Test
    void checkCreateUser() {

        String createData = "{\"name\": \"Neo\", " +
                "\"job\": \"Messiah\"}";

        given()
                .body(createData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("Neo"))
                .body("job", is("Messiah"));
    }

    @Test
    void checkNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    void checkDelete() {
        given()
                .when()
                .delete("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(204);
    }


    @Test
    void checkListUser() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.findAll {it.id<10}.first_name", hasItems("Michael", "Tobias"));
    }


}
