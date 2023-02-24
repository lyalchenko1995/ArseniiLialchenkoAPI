package specifications;

import io.restassured.specification.RequestSpecification;

public class CardSpec extends BaseSpec {

    public RequestSpecification getCardCreateSpec(String cardName, String listId) {
        return baseRequestBuilder
                .setBasePath("/cards")
                .addQueryParam(parameterCardName, cardName)
                .addQueryParam(parameterCardList, listId)
                .setBody("")
                .build();
    }

    public RequestSpecification getCardGetSpec() {
        return baseRequestBuilder
                .setBasePath("/cards/{id}")
                .build();
    }
}
