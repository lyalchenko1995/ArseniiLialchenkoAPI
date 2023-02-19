package specifications.boardSpecification;

import specifications.BaseSpec;
import io.restassured.specification.RequestSpecification;

public class CreateBoardSpec extends BaseSpec {
    public RequestSpecification getBoardCreateSpec() {
        return baseRequestBuilder
                .setBasePath("/1/boards/")
                .setBody("")
                .build();
    }
}
