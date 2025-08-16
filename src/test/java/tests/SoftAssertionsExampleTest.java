package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    String route;
    String firstName;
    String lastName;
    String email;
    String gender;
    String phoneNumber;
    String year;
    String month;
    String day;
    String subjectScience;
    String subjectEnglish;
    String subjectArts;
    String hobbieReading;
    String imageName;
    String currentAddress;
    String state;
    String city;

    @BeforeAll
    static void setupConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setupUserData() {
        firstName = "Julia";
        lastName = "K";
        email = "test_email@ya.ru";
        gender = "Female";
        phoneNumber = "7900900909";
        year = "1989";
        month = "February";
        day = "6";
        subjectScience = "Computer Science";
        subjectEnglish = "English";
        subjectArts = "Arts";
        hobbieReading = "Reading";
        imageName = "cat.jpg";
        currentAddress = "Ryazan city";
        state = "Rajasthan";
        city = "Jaipur";
    }

    @Test
    void fillFormTest() {
        route = "/automation-practice-form";

        open(route);

        // user name
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);

        // email
        $("#userEmail").setValue(email);

        // gender
        $("#genterWrapper").$(byText(gender)).click();

        // phone number
        $("#userNumber").setValue(phoneNumber);

        // date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker").shouldBe(visible);

        $("select[class*='year']").selectOption(year);
        $("select[class*='month']").selectOption(month);

        $$(".react-datepicker__week").get(1)
                .$$("[role='option']")
                .find(exactText(day))
                .click();

        $(".react-datepicker").shouldNotBe(visible);

        // subjects
        $("#subjectsInput").setValue(subjectScience).pressEnter();
        $("#subjectsInput").setValue(subjectEnglish).pressEnter();
        $("#subjectsInput").setValue(subjectArts).pressEnter();
        $(".subjects-auto-complete_menu").shouldNotBe(visible);

        // hobbies
        $("#hobbiesWrapper").$(byText(hobbieReading)).click();

        // picture
        $("#uploadPicture").uploadFromClasspath(imageName);

        // current address
        $("#currentAddress").setValue(currentAddress);

        // state and city
        $("#state").scrollTo().click();
        $("#state")
                .find("[class*='menu']")
                .shouldBe(visible)
                .$(byText(state)).click();

        $("#city").click();
        $("#city")
                .find("[class*='menu']")
                .shouldBe(visible)
                .$(byText(city)).click();

        // submit
        $("#submit").click();

        // check data in modal window

        $(".modal-content").shouldBe(visible);

        assertTableEntry("Student Name", firstName + " " + lastName);
        assertTableEntry("Student Email", email);
        assertTableEntry("Gender", gender);
        assertTableEntry("Mobile", phoneNumber);
        assertTableEntry("Date of Birth", "0" + day + " " + month + "," + year);
        assertTableEntry("Subjects", subjectScience + ", " + subjectEnglish + ", " + subjectArts);
        assertTableEntry("Hobbies", hobbieReading);
        assertTableEntry("Picture", imageName);
        assertTableEntry("Address", currentAddress);
        assertTableEntry("State and City", state + " " + city);
    }

    private void assertTableEntry(String label, String value) {
        $$("tr").find(text(label))
                .$$("td").get(1)
                .shouldHave(exactText(value));
    }
}
