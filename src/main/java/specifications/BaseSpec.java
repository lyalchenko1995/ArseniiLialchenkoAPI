package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import java.net.HttpURLConnection;


public abstract class BaseSpec {

    public static final String URL = "https://api.trello.com";

    public static final String parameterBoardName = "name";
    public static final String parameterBoardId = "id";

    public static final String parameterListId = "id";
    public static final String parameterListName = "name";

    public static final String parameterCardList = "idList";
    public static final String parameterCardName = "name";

    protected RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder()
            .setBaseUri(URL)
            .addQueryParams(Authentification.getAuthentificationParameters());

    public ResponseSpecification getResponseSpecCheck() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpURLConnection.HTTP_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

}
