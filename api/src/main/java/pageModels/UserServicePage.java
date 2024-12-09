package main.java.pageModels;


import main.java.servicesPOJO.UserRoot;
import main.java.specification.Specifications;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UserServicePage extends BasePage {
    private static UserRoot userRootRequest;

    public UserServicePage viewPage() {
        Specifications.installSpecification(Specifications.requestSpec(USER_SERVICE_URL), Specifications.responseSpec200());
        userRootRequest = given()
                .when()
                .get("api/users?page=1")
                .then().log().all()
                .extract().as(UserRoot.class);
        return this;
    }

    public UserServicePage checkEmailTest() {
        Assert.assertTrue(userRootRequest.data.stream().allMatch(x -> x.getEmail()
                .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.+[a-zA-Z]{1,}$")));
        return this;
    }

    public UserServicePage checkAndsEmailTest() {
        Assert.assertTrue(userRootRequest.data.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")), "Не верный email");
        return this;
    }

    public UserServicePage checkAvatarAndIdTest() {
        userRootRequest.data.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        return this;
    }

    public UserServicePage checkFirstNameTest() {
        Assert.assertTrue(userRootRequest.data.stream().allMatch(x -> x.getFirst_name().matches("[a-zA-Z]{1,}")));
        return this;
    }

    public UserServicePage checkLastNameTest() {
        Assert.assertTrue(userRootRequest.data.stream().allMatch(x -> x.getLast_name().matches("[a-zA-Z]{1,}")));
        return this;
    }

    public UserServicePage checkPerPageAndDataTest() {
        Assert.assertTrue(userRootRequest.per_page == userRootRequest.data.size());
        return this;
    }
}