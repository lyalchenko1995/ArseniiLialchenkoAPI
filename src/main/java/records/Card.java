package records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Card(
        String id,
        boolean closed,
        String idBoard,
        String idList,
        String name,
        String url
) {
}
