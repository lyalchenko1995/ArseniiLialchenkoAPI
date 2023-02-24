package specifications;

import io.restassured.specification.RequestSpecification;

public class BoardSpec extends BaseSpec {

    private static final String BOARDS = "/boards";
    private static final String BOARDS_ID = BOARDS + "/{id}";
    private static final String BOARD_LISTS = BOARDS_ID + "/lists";
    public RequestSpecification getBoardCreateSpec() {
        return baseRequestBuilder
                .setBasePath(BOARDS)
                .build();
    }

    public RequestSpecification getBoardDeleteSpec() {
        return baseRequestBuilder
                .setBasePath(BOARDS_ID)
                .build();
    }

    public RequestSpecification getBoardGetSpec() {
        return baseRequestBuilder
                .setBasePath(BOARDS_ID)
                .build();
    }

    public RequestSpecification getBoardListsSpec() {
        return baseRequestBuilder
                .setBasePath(BOARD_LISTS)
                .build();
    }

    public RequestSpecification getBoardUpdateSpecForBuilder() {
        return baseRequestBuilder
                .setBasePath(BOARDS_ID)
                .build();
    }
}
