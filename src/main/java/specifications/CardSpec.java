package specifications;

import io.restassured.specification.RequestSpecification;

public class CardSpec extends BaseSpec {

    public RequestSpecification getCardCreateSpec(String cardName, String listId) {
        return baseRequestBuilder
                .setBasePath("/cards")
                .addQueryParam(NAME, cardName)
                .addQueryParam(CARD_LIST, listId)
                .setBody("")
                .build();
    }

    public RequestSpecification getCardGetSpec() {
        return baseRequestBuilder
                .setBasePath("/cards/{id}")
                .build();
    }
}
