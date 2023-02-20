import org.testng.Assert;
import org.testng.annotations.Test;
import records.Card;
import specifications.CardSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.parameterCardList;
import static specifications.BaseSpec.parameterCardName;

public class CardTest extends BaseTest {

    private String createdCardId;
    private Card createdCard;
    private String newCardName = "Card name";
    private CardSpec cardSpec = new CardSpec();

    @Test(priority = 0)
    public void createCardTest() {
        var createCardResponse = given()
                .spec(cardSpec.getCardCreateSpec(newCardName, listId))
                .when()
                .post()
                .then()
                .spec(cardSpec.getResponseSpecCheck())
                .body(parameterCardName, equalTo(newCardName))
                .body(parameterCardList, equalTo(listId));
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
                .spec(cardSpec.getResponseSpecCheck());
        Assert.assertEquals(createdCard, getCardResponse.extract().body().as(Card.class));
    }
}
