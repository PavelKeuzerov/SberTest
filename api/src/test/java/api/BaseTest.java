package test.java.api;


import main.java.pageModels.UserServicePage;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected UserServicePage userServicePage;

    @BeforeClass
    public void setUp(){
        userServicePage = new UserServicePage();
    }
}