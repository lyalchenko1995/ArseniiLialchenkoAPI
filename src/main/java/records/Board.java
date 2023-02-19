package records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Board (
        String id,
        String name,
        String desc,
        String descData,
        boolean closed,
        String url,
        String shortUrl) {
}
