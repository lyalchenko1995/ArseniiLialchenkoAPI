import org.testng.Assert;
import org.testng.annotations.Test;
import records.Card;
import specifications.CardSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.*;

public class CardTest extends BaseTest {

    private String createdCardId;
    private Card createdCard;
    private final CardSpec cardSpec = new CardSpec();

    @Test()
    public void createCardTest() {
        String newCardName = "Card name";
        var createCardResponse = given()
                .spec(cardSpec.getCardCreateSpec(newCardName, listId))
                .when()
                .post()
                .then()
                .spec(cardSpec.responseOkJson())
                .body(NAME, equalTo(newCardName))
                .body(CARD_LIST, equalTo(listId));
        createdCard = createCardResponse.extract().body().as(Card.class);
        createdCardId = createdCard.id();
    }

    @Test(priority = 1)
    public void getCardTest() {
        var getCardResponse = given()
                .spec(cardSpec.getCardGetSpec())
                .pathParam("id", createdCardId)
                .when()
                .get()
                .then()
                .spec(cardSpec.responseOkJson());
        Assert.assertEquals(createdCard, getCardResponse.extract().body().as(Card.class));
    }
}
