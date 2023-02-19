package specifications.listSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class CreateListSpec extends BaseSpec {

    public RequestSpecification getListCreateSpec(String name) {
        return baseRequestBuilder
                .setBasePath("/1/boards/{id}/lists")
                .addQueryParam(parameterListName, name)
                .setBody("")
                .build();
    }

}
