package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsExampleTest {
    String route;

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1980x920";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void CheckExamplpeTest() {
        route = "/selenide/selenide";
        open(route);

        $("#wiki-tab")
                .click();
        $("#wiki-pages-box button.Link--muted")
                .hover()
                .click();

        $("#wiki-pages-box")
                .shouldHave(text("SoftAssertions"));

        $("#wiki-pages-box").$("details:last-child")
                .click();
        $("#wiki-pages-box").$$("a")
                .findBy(text("SoftAssertions"))
                .click();

        $(".markdown-body")
                .$$("h4")
                .findBy(text("JUnit5"))
                .closest(".markdown-heading")
                .sibling(0)
                .$("pre")
                .shouldBe(visible)
                .shouldHave(text("SoftAssertsExtension"));
    }
}
