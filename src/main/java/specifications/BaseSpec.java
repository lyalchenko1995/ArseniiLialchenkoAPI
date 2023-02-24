package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.net.HttpURLConnection;


public abstract class BaseSpec {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CARD_LIST = "idList";

    protected RequestSpecBuilder baseRequestBuilder = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addQueryParams(Authentication.getAuthenticationParameters());

    public ResponseSpecification responseOkJson() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpURLConnection.HTTP_OK)
                .expectResponseTime(Matchers.lessThan(500L))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public ResponseSpecification responseOk() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectResponseTime(Matchers.lessThan(500L))
            .build();
    }

    public ResponseSpecification responseNotFound() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_NOT_FOUND)
            .expectContentType(ContentType.TEXT)
            .expectResponseTime(Matchers.lessThan(500L))
            .build();
    }
}
