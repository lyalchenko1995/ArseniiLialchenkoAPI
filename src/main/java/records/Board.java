package records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
        String id;
        String name;
        String desc;
}
