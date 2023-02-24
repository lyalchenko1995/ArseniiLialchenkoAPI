import org.testng.annotations.Test;
import specifications.ListSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.*;

public class ListTest extends BaseTest {

    private String createdListId;
    private final String newListName = "List name";

    private final ListSpec listSpec = new ListSpec();

    @Test()
    public void createListTest() {
        var createListResponse = given()
                .spec(listSpec.getListCreateSpec(newListName))
                .pathParam(ID, beforeBoardId)
                .when()
                .post()
                .then()
                .spec(listSpec.responseOkJson())
                .body(NAME, equalTo(newListName));
        createdListId = createListResponse.extract().body().path("id");
    }

    @Test(priority = 1)
    public void getListTest() {
        given()
                .spec(listSpec.getListGetSpec())
                .pathParam("id", createdListId)
                .when()
                .get()
                .then()
                .spec(listSpec.responseOkJson())
                .body(NAME, equalTo(newListName))
                .body(ID, equalTo(createdListId));
    }
}
