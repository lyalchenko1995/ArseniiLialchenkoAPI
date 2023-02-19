package specifications.boardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class UpdateBoardSpec extends BaseSpec {

    public RequestSpecification getBoardUpdateSpec(String newName) {
        return baseRequestBuilder
                .setBasePath("/1/boards/{id}")
                .addQueryParam(parameterBoardName, newName)
                .setBody("")
                .build();
    }

}
