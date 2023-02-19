package specifications.boardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class GetBoardSpec extends BaseSpec {

    private static String path = "/1/boards/{id}";

    public RequestSpecification getBoardGetSpec() {
        return baseRequestBuilder
                .setBasePath(path)
                .build();
    }

}
