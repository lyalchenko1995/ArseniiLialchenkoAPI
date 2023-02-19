package specifications.cardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class CreateCardSpec extends BaseSpec {

    public RequestSpecification getCardCreateSpec(String cardName, String listId) {
        return baseRequestBuilder
                .setBasePath("/1/cards")
                .addQueryParam(parameterCardName, cardName)
                .addQueryParam(parameterCardList, listId)
                .setBody("")
                .build();
    }

}
