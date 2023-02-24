
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import records.Board;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.*;

public class BoardTest extends BaseTest{
    private String initialBoardName;
    private static String boardId;

    @BeforeClass
    @Override
    public void testSetUp() {
        // we don't need to create board and list in this test from super class
        initialBoardName = RandomStringUtils.random(80);
    }

    @AfterClass
    @Override
    public void tearDown() {
        // nothing to delete here
    }

    @Test()
    public void createBoardTest() {
        boardId =
            given()
                    .spec(boardSpec.getBoardCreateSpec())
                    .queryParam(NAME, initialBoardName)
                .when()
                    .post()
                .then()
                    .spec(boardSpec.responseOkJson())
                    .body(NAME, equalTo(initialBoardName))
                    .extract().body().as(Board.class).getId();
    }

    @Test(priority = 1)
    public void updateBoardTest() {
        var descForBoard = RandomStringUtils.random(100, true, true);
        initialBoardName = RandomStringUtils.random(100);
        var newBoard = Board.builder().desc(descForBoard).id(boardId).name(initialBoardName).build();

        given()
            .spec(boardSpec.getBoardUpdateSpecForBuilder())
            .when()
                .pathParam(ID, boardId)
                .body(newBoard)
                .put()
            .then()
                .body("desc",equalTo(descForBoard))
                .body(NAME, equalTo(initialBoardName))
                .spec(boardSpec.responseOkJson());
    }

    @Test(priority = 2)
    public void getBoardTest() {
        given()
            .spec(boardSpec.getBoardGetSpec())
                .pathParam(ID, boardId)
            .when()
                .get()
            .then()
                .spec(boardSpec.responseOkJson())
                .body(NAME, equalTo(initialBoardName));
    }

    @Test(priority = 3)
    public void deleteBoardTest() {
        given()
            .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(ID, boardId)
            .when()
                .delete()
            .then()
                .spec(boardSpec.responseOkJson())
                .body("_value", nullValue());
    }

    @Test(priority = 4)
    public void deleteDeletedBoardTest() {
        given()
            .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(ID, boardId)
            .when()
                .delete()
            .then()
                .spec(boardSpec.responseNotFound());
    }
}
