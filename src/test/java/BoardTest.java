
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
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
        boardId = createResponse.getId();
    }

    @Test(priority = 1)
    public void updateBoardTest() {
        String descForBoard = RandomStringUtils.random(100, true, true);

        var newBoard = Board.builder().desc(descForBoard).id(boardId).name(updatedBoardName).build();

        System.out.println(newBoard.toString());
        given()
                .spec(boardSpec.getBoardUpdateSpecForBuilder())
                .when()
                .pathParam(boardIdUrlParamName, boardId)
                .body(newBoard)
                .put()
                .then()
                .body("desc",equalTo(descForBoard))
                .body(parameterBoardName, equalTo(updatedBoardName))
                .spec(boardSpec.getResponseSpecCheck())
                .extract().body().as(Board.class);

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

    @Test(priority = 4)
    public void deleteDeletedBoardTest() {
        given()
                .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(boardIdUrlParamName, boardId)
                .when()
                .delete()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
