package specifications.cardSpecification;

import io.restassured.specification.RequestSpecification;
import specifications.BaseSpec;

public class GetCardSpec extends BaseSpec {

    public RequestSpecification getCardGetSpec() {
        return baseRequestBuilder
                .setBasePath("/1/cards/{id}")
                .build();
    }

}
