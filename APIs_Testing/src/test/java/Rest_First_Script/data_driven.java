package Rest_First_Script;

import static io.restassured.RestAssured.*;
import Data.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class data_driven {
    @Test(dataProvider = "test_data")
    public void datadriven_testcase(String customer_id
    ,String firstName, String lastName, String street, String city, String state,
                                    String zipCode, String phoneNumber, String ssn){
        String xml = get("https://parabank.parasoft.com/parabank/services/bank/customers/" + customer_id + "/").andReturn().asString();
        XmlPath xml_path = new XmlPath(xml).setRoot("customer");
        String customerID = xml_path.getString("id");
        String first_Name = xml_path.getString("firstName");
        String last_Name = xml_path.getString("lastName");
        String street_of_customer = xml_path.getString("address.street");
        String city_of_customer = xml_path.getString("address.city");
        String state_of_customer = xml_path.getString("address.state");
        String zipcode_of_customer = xml_path.getString("address.zipcode");
        String Phone_num = xml_path.getString("phoneNumber");
        String ssn_num = xml_path.getString("ssn");
        Assert.assertEquals(customerID, customer_id);
        Assert.assertEquals(first_Name, firstName);
        Assert.assertEquals(last_Name, lastName);
        Assert.assertEquals(street_of_customer, street);
        Assert.assertEquals(city_of_customer, city);
        Assert.assertEquals(state_of_customer, state);
        Assert.assertEquals(zipcode_of_customer, zipCode);
        Assert.assertEquals(Phone_num, phoneNumber);
        Assert.assertEquals(ssn_num, ssn);

    }

    @Test
    public void validate_invalid_response(){
        String response_data = RestAssured
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/12214").asString();
        Assert.assertEquals(response_data,"Could not find customer #12214");
    }
    @DataProvider
    public String[][]test_data() throws IOException, InvalidFormatException {
        ExcelReader rd = new ExcelReader();
        return rd.data_Driven();
    }
}
