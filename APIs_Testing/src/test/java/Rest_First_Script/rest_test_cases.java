package Rest_First_Script;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class rest_test_cases {


    @Test
    public void validate_response_code_testcase1(){
        given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").then()
                .assertThat().statusCode(200);
    }
    @Test
    public void validate_response_code_testcase2(){
        Response resp_body = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12212");
        Assert.assertEquals(resp_body.statusCode(),200);
    }
    @Test
    public void validate_response_data_testcase3(){
        given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").then()
                .assertThat().body("customer.id",equalTo("12212")).and().
        assertThat().body("customer.firstName", equalTo("John")).and().
        assertThat().body("customer.lastName", equalTo("Smith")).and().
        assertThat().body("customer.address.street", equalTo("1431 Main St")).and().
        assertThat().body("customer.address.city", equalTo("Beverly Hills")).and().
        assertThat().body("customer.address.state", equalTo("CA")).and().
        assertThat().body("customer.address.zipCode", equalTo("90210")).and().
        assertThat().body("customer.phoneNumber", equalTo("310-447-4121")).and().
        assertThat().body("customer.ssn", equalTo("622-11-9999"));
    }

    @Test
    public void validate_invalid_response(){
        String response_data = RestAssured
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/12214").asString();
        Assert.assertEquals(response_data,"Could not find customer #12214");
    }

}
