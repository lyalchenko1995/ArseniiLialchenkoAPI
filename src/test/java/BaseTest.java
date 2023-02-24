import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import records.Board;
import specifications.BoardSpec;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specifications.BaseSpec.*;

public abstract class BaseTest {

    protected static String beforeBoardId;
    protected static String listId;
    protected BoardSpec boardSpec;

    @Parameters({"baseUri"})
    @BeforeSuite
    public void setUp(String baseUri) {
        // as example of parametrization. Think how to insert env depended tokens
        RestAssured.baseURI = baseUri;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeClass
    public void testSetUp() {
        boardSpec = new BoardSpec();
        GenerateTestData();
    }

    @AfterClass
    public void tearDown() {
        given()
                .spec(boardSpec.getBoardDeleteSpec())
                .pathParam(ID, beforeBoardId)
                .when()
                .delete()
                .then()
                .spec(boardSpec.responseOk());
    }

    private void GenerateTestData() {
        beforeBoardId = given()
            .spec(boardSpec.getBoardCreateSpec())
                .queryParam(NAME, RandomStringUtils.random(60))
            .when()
                .post()
            .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().body().as(Board.class).getId();
        listId = getFirstListId(beforeBoardId);
    }
    private String getFirstListId(String boardId) {
        ArrayList<String> resp = given()
            .spec(boardSpec.getBoardListsSpec())
                .pathParam(ID, boardId)
            .when()
                .get()
            .then()
                .statusCode(HttpURLConnection.HTTP_OK) // change to response spec
                .extract().body().path(ID);
        return resp.get(0);
    }
}
