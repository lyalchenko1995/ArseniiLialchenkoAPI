package specifications.boardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class GetListsOfBoardSpec extends BaseSpec {

    public RequestSpecification getBoardListsSpec() {
        return baseRequestBuilder
                .setBasePath("/1/boards/{id}/lists")
                .build();
    }

}
