import org.testng.Assert;
import org.testng.annotations.Test;
import records.Card;
import specifications.cardSpecification.CreateCardSpec;
import specifications.cardSpecification.GetCardSpec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specifications.BaseSpec.parameterCardList;
import static specifications.BaseSpec.parameterCardName;

public class CardTest extends BaseTest {

    String createdCardId;
    Card createdCard;
    String newCardName = "Card name";
    CreateCardSpec createCardSpecs = new CreateCardSpec();
    GetCardSpec getCardSpecs = new GetCardSpec();

    @Test(priority = 0)
    public void createCardTest() {
        var createCardResponse = given()
                .spec(createCardSpecs.getCardCreateSpec(newCardName, listId))
                .when()
                .post()
                .then()
                .spec(createCardSpecs.getResponseSpecCheck())
                .body(parameterCardName, equalTo(newCardName))
                .body(parameterCardList, equalTo(listId));
        createdCard = createCardResponse.extract().body().as(Card.class);
        createdCardId = createdCard.id();
    }

    @Test(priority = 1)
    public void getCardTest() {
        var getCardResponse = given()
                .spec(getCardSpecs.getCardGetSpec())
                .pathParam("id", createdCardId)
                .when()
                .get()
                .then()
                .spec(getCardSpecs.getResponseSpecCheck());
        Assert.assertEquals(createdCard, getCardResponse.extract().body().as(Card.class));
    }
}
