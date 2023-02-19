import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import records.Board;
import specifications.boardSpecification.CreateBoardSpec;
import specifications.boardSpecification.DeleteBoardSpec;
import specifications.boardSpecification.GetListsOfBoardSpec;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.*;

public abstract class BaseTest {

    static String beforeBoardId;
    static final String boardIdUrlParamName = "id";
    static final String beforeBoardName = "BeforeTestBoardName";
    static String listId;
    static final String listIdUrlParamName = "id";

    CreateBoardSpec createBoardSpec = new CreateBoardSpec();
    DeleteBoardSpec deleteBoardSpec = new DeleteBoardSpec();
    GetListsOfBoardSpec getListsForBoard = new GetListsOfBoardSpec();


    @BeforeClass
    public void setup() {
        Board testBoard = given()
                .spec(createBoardSpec.getBoardCreateSpec())
                .queryParam(parameterBoardName, beforeBoardName)
                .when()
                .post()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().body().as(Board.class);

        beforeBoardId = testBoard.id();
        listId = getFirstListId(beforeBoardId);
    }

    @AfterClass
    public void tearDown() {
        given()
                .spec(deleteBoardSpec.getBoardDeleteSpec())
                .pathParam(boardIdUrlParamName, beforeBoardId)
                .when()
                .delete()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    public String getFirstListId(String boardId) {
        ArrayList<String> resp = given()
                .spec(getListsForBoard.getBoardListsSpec())
                .pathParam(parameterBoardId, boardId)
                .when()
                .get()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().body().path(parameterListId);
        return resp.get(0);
    }
}
