package specifications;

import io.restassured.specification.RequestSpecification;

public class ListSpec extends BaseSpec {

    public RequestSpecification getListCreateSpec(String name) {
        return baseRequestBuilder
                .setBasePath("/boards/{id}/lists")
                .addQueryParam(parameterListName, name)
                .setBody("")
                .build();
    }

    public RequestSpecification getListGetSpec() {
        return baseRequestBuilder
                .setBasePath("/lists/{id}")
                .setBody("")
                .build();
    }

}
