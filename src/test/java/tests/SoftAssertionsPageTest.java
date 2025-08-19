package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoftAssertionsPageTest {
    String route;

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void checkExampleTest() {
        route = "/selenide/selenide";

        String expectedSnippet = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }""";

        open(route);

        $("#wiki-tab")
                .click();
        $("#wiki-pages-box button.Link--muted")
                .click();

        $("#wiki-pages-box")
                .shouldHave(text("SoftAssertions"));

        $("#wiki-pages-box").$$("a")
                .findBy(text("SoftAssertions"))
                .click();

        SelenideElement jUnit5Snippet = $(".markdown-body")
                .$$("h4")
                .findBy(text("JUnit5"))
                .closest(".markdown-heading")
                .sibling(0)
                .$("pre");

        jUnit5Snippet.shouldHave(exactText(expectedSnippet));
    }
}
