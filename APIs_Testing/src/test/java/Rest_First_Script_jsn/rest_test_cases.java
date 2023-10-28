package Rest_First_Script_jsn;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class rest_test_cases {

    //smoke testcase
    @Test
    public void validate_response_code_testcase(){
        given().get("https://reqres.in/api/users?page=2").then()
                .assertThat().statusCode(200);
    }
    //positive testcase or functional testcase
    @Test
    public void validate_data_response_testcase(){
        given().get("https://reqres.in/api/users?page=2").then()
                .assertThat().body("data[0].'email'",equalTo("michael.lawson@reqres.in")).and()
                .assertThat().body("data[0].'first_name'",equalTo("Michael")).and().assertThat()
                .body("data[0].'last_name'",equalTo("Lawson")).and().assertThat()
                .body("data[0].'avatar'",equalTo("https://reqres.in/img/faces/7-image.jpg"));
    }
    //negative scenario
    @Test
    public void invalid_data_response_testcase(){
        String response = RestAssured.get("https://reqres.in/api/users?page=100000").andReturn().asString();
        Assert.assertTrue(response.contains("contributions towards server costs are appreciated!"));

    }
}
