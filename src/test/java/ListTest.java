import org.testng.annotations.Test;
import specifications.CardSpec;
import specifications.ListSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.*;

public class ListTest extends BaseTest {

    protected String createdListId;
    protected String newListName = "List name";

    protected ListSpec listSpec = new ListSpec();

    @Test(priority = 0)
    public void createListTest() {
        var createListResponse = given()
                .spec(listSpec.getListCreateSpec(newListName))
                .pathParam(boardIdUrlParamName, beforeBoardId)
                .when()
                .post()
                .then()
                .spec(listSpec.getResponseSpecCheck())
                .body(parameterListName, equalTo(newListName));
        createdListId = createListResponse.extract().body().path("id");
    }

    @Test(priority = 1)
    public void getListTest() {
        given()
                .spec(listSpec.getListGetSpec())
                .pathParam(listIdUrlParamName, createdListId)
                .when()
                .get()
                .then()
                .spec(listSpec.getResponseSpecCheck())
                .body(parameterListName, equalTo(newListName))
                .body(parameterListId, equalTo(createdListId));
    }
}
