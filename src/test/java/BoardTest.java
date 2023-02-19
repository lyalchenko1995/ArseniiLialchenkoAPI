
import org.testng.annotations.Test;
import records.Board;
import specifications.boardSpecification.CreateBoardSpec;
import specifications.boardSpecification.GetBoardSpec;
import specifications.boardSpecification.UpdateBoardSpec;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.parameterBoardName;

public class BoardTest extends BaseTest{

    static final String boardName = "Board name";
    static final String updatedBoardName = "New board name";
    static String boardId;

    CreateBoardSpec createBoardSpec = new CreateBoardSpec();
    GetBoardSpec getBoardSpec = new GetBoardSpec();
    UpdateBoardSpec updateBoardSpecs = new UpdateBoardSpec();

    @Test(priority = 0)
    public void createBoardTest() {
        var createResponse = given()
                .spec(createBoardSpec.getBoardCreateSpec())
                .queryParam(parameterBoardName, boardName)
                .when()
                .post()
                .then()
                .spec(createBoardSpec.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(boardName))
                .extract().body().as(Board.class);
        boardId = createResponse.id();
    }

    @Test(priority = 1)
    public void updateBoardTest() {
        given()
                .spec(updateBoardSpecs.getBoardUpdateSpec(updatedBoardName))
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .put()
                .then()
                .spec(updateBoardSpecs.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(updatedBoardName));
    }

    @Test(priority = 2)
    public void getBoardTest() {
        given()
                .spec(getBoardSpec.getBoardGetSpec())
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .get()
                .then()
                .spec(getBoardSpec.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(updatedBoardName));
    }

    @Test(priority = 3)
    public void deleteBoardTest() {
        given()
                .spec(deleteBoardSpec.getBoardDeleteSpec())
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .delete()
                .then()
                .spec(deleteBoardSpec.getResponseSpecCheck())
                .body("_value", nullValue());
    }
}
