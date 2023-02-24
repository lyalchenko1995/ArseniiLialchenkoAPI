package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import java.net.HttpURLConnection;


public abstract class BaseSpec {

    protected static final String BASE_URL = "https://api.trello.com/1";

    public static final String parameterBoardName = "name";
    public static final String parameterBoardId = "id";

    public static final String parameterListId = "id";
    public static final String parameterListName = "name";

    public static final String parameterCardList = "idList";
    public static final String parameterCardName = "name";

    protected RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addQueryParams(Authentification.getAuthentificationParameters());

    public ResponseSpecification getResponseSpecCheck() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpURLConnection.HTTP_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

}
