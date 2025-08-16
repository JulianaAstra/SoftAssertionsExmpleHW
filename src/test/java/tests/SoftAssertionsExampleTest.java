package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsExampleTest {
    String route;

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1560x900";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setupUserData() {

    }

    @Test
    void CheckExamplpeTest() {
        route = "/selenide/selenide";
        open(route);
        $("#wiki-tab").click();
        $("#wiki-pages-box button.Link--muted").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        $("#wiki-pages-box").$("details:last-child").click();
        $("#wiki-pages-box").$$("a").findBy(text("SoftAssertions")).click();

        $$("h1").findBy(visible).shouldHave(text("SoftAssertions"));
    }
}
