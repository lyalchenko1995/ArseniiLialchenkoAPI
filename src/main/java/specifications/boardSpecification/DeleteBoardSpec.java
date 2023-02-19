package specifications.boardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class DeleteBoardSpec extends BaseSpec {

    public RequestSpecification getBoardDeleteSpec() {
        return baseRequestBuilder
                .setBasePath("/1/boards/{id}")
                .build();
    }

}
