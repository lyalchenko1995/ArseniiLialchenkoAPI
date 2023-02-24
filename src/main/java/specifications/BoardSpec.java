package specifications;

import io.restassured.specification.RequestSpecification;

public class BoardSpec extends BaseSpec {

    public RequestSpecification getBoardCreateSpec() {
        return baseRequestBuilder
                .setBasePath("/boards/")
                .setBody("")
                .build();
    }

    public RequestSpecification getBoardDeleteSpec() {
        return baseRequestBuilder
                .setBasePath("/boards/{id}")
                .build();
    }

    public RequestSpecification getBoardGetSpec() {
        return baseRequestBuilder
                .setBasePath("/boards/{id}")
                .build();
    }

    public RequestSpecification getBoardListsSpec() {
        return baseRequestBuilder
                .setBasePath("/boards/{id}/lists")
                .build();
    }

    public RequestSpecification getBoardUpdateSpec(String newName) {
        return baseRequestBuilder
                .setBasePath("/boards/{id}")
                .addQueryParam(parameterBoardName, newName)
                .setBody("")
                .build();
    }

    public RequestSpecification getBoardUpdateSpecForBuilder() {
        return baseRequestBuilder
                .setBasePath("/boards/{id}")
                .build();
    }
}
