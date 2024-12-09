package test.java.api.userService;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import main.java.specification.Specifications;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AvailableReqresTestNoPojoTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkReqresTestNoPojoTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec200());
        Response response = given()
                .when()
                .get("api/users?page=1")
                .then()
                .body("per_page", equalTo(6))
                .body("page", equalTo(1))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");
        List<String> firstNames = jsonPath.get("data.first_name");
        List<String> lastNames = jsonPath.get("data.last_name");
        int pages = jsonPath.get("per_page");

//        1)Что почта всех пользователей имеет корректный формат и домен reqres.in
        Assert.assertTrue(emails.stream().allMatch(x -> x
                .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.+[a-zA-Z]{1,}$")));
        Assert.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));

//        2)Что в avatar присутствует id
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

//        3)Что per_page равен количеству объектов в списке data
        Assert.assertTrue(pages == ids.size());

//        4)Что first_name и last_name содержат только латинские буквы
        Assert.assertTrue(firstNames.stream().allMatch(x -> x.matches("[a-zA-Z]{1,}")));
        Assert.assertTrue(lastNames.stream().allMatch(x -> x.matches("[a-zA-Z]{1,}")));
    }
}