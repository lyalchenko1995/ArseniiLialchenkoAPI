import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import records.Board;
import specifications.BoardSpec;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.*;

public abstract class BaseTest {

    protected static String beforeBoardId;
    protected static final String boardIdUrlParamName = "id";
    protected static final String beforeBoardName = "BeforeTestBoardName";
    protected static String listId;
    protected static final String listIdUrlParamName = "id";

    BoardSpec boardSpec = new BoardSpec();

    @BeforeClass
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        Board testBoard = given()
                .spec(boardSpec.getBoardCreateSpec())
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
                .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(boardIdUrlParamName, beforeBoardId)
                .when()
                .delete()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    public String getFirstListId(String boardId) {
        ArrayList<String> resp = given()
                .spec(boardSpec.getBoardListsSpec())
                .pathParam(parameterBoardId, boardId)
                .when()
                .get()
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().body().path(parameterListId);
        return resp.get(0);
    }
}
