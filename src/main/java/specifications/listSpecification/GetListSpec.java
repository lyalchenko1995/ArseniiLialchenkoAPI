package specifications.listSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class GetListSpec extends BaseSpec {

    public RequestSpecification getListGetSpec() {
        return baseRequestBuilder
                .setBasePath("/1/lists/{id}")
                .setBody("")
                .build();
    }

}
