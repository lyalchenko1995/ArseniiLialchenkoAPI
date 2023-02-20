
import org.testng.annotations.Test;
import records.Board;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.parameterBoardName;

public class BoardTest extends BaseTest{

    private static final String boardName = "Board name";
    private static final String updatedBoardName = "New board name";
    private static String boardId;

    @Test(priority = 0)
    public void createBoardTest() {
        var createResponse = given()
                .spec(boardSpec.getBoardCreateSpec())
                .queryParam(parameterBoardName, boardName)
                .when()
                .post()
                .then()
                .spec(boardSpec.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(boardName))
                .extract().body().as(Board.class);
        boardId = createResponse.id();
    }

    @Test(priority = 1)
    public void updateBoardTest() {
        given()
                .spec(boardSpec.getBoardUpdateSpec(updatedBoardName))
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .put()
                .then()
                .spec(boardSpec.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(updatedBoardName));
    }

    @Test(priority = 2)
    public void getBoardTest() {
        given()
                .spec(boardSpec.getBoardGetSpec())
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .get()
                .then()
                .spec(boardSpec.getResponseSpecCheck())
                .body(parameterBoardName, equalTo(updatedBoardName));
    }

    @Test(priority = 3)
    public void deleteBoardTest() {
        given()
                .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .delete()
                .then()
                .spec(boardSpec.getResponseSpecCheck())
                .body("_value", nullValue());
    }
}
