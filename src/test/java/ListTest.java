import org.testng.annotations.Test;
import specifications.listSpecification.CreateListSpec;
import specifications.listSpecification.GetListSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.*;

public class ListTest extends BaseTest {

    String createdListId;
    String newListName = "List name";

    CreateListSpec createListSpecs = new CreateListSpec();
    GetListSpec getListSpecs = new GetListSpec();

    @Test(priority = 0)
    public void createListTest() {
        var createListResponse = given()
                .spec(createListSpecs.getListCreateSpec(newListName))
                .pathParam(boardIdUrlParamName, beforeBoardId)
                .when()
                .post()
                .then()
                .spec(createListSpecs.getResponseSpecCheck())
                .body(parameterListName, equalTo(newListName));
        createdListId = createListResponse.extract().body().path("id");
    }

    @Test(priority = 1)
    public void getListTest() {
        given()
                .spec(getListSpecs.getListGetSpec())
                .pathParam(listIdUrlParamName, createdListId)
                .when()
                .get()
                .then()
                .spec(getListSpecs.getResponseSpecCheck())
                .body(parameterListName, equalTo(newListName))
                .body(parameterListId, equalTo(createdListId));
    }
}
