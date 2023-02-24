package specifications;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Authentication {
    private static final Map<String, String> authQueryParams = new HashMap<>();

    public static Map<String, String> getAuthenticationParameters() {
        if (authQueryParams.isEmpty()) {
            authQueryParams.put("key", System.getenv("APIkey"));
            authQueryParams.put("token", System.getenv("APItoken"));
        }
        return authQueryParams;
    }

}
