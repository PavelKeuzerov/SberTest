package test.java.api.userService;


import org.testng.annotations.Test;
import test.java.api.BaseTest;

public class AvailableReqresTest extends BaseTest {

    @Test(description = "Тест проверяет" +
            "1)Что почта всех пользователей имеет корректный формат и домен reqres.in " +
            "2)Что в avatar присутствует id" +
            "3)Что per_page равен количеству объектов в списке data " +
            "4)Что first_name и last_name содержат только латинские буквы")

    public void checkReqresTest() {
        userServicePage
                .viewPage()
                .checkEmailTest()
                .checkAndsEmailTest()
                .checkAvatarAndIdTest()
                .checkPerPageAndDataTest()
                .checkFirstNameTest()
                .checkLastNameTest();
    }
}