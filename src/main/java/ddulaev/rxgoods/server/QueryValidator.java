package ddulaev.rxgoods.server;

import java.util.List;
import java.util.Map;

public class QueryValidator {

    public static boolean validateUser(Map<String, List<String>> paramsMap) {
        return paramsMap.containsKey("id") && paramsMap.containsKey("name") && paramsMap.containsKey("currency");
    }

    public static boolean validateProduct(Map<String, List<String>> paramsMap) {
        return paramsMap.containsKey("id") && paramsMap.containsKey("cost_usd") && paramsMap.containsKey("name");
    }

    public static boolean validateGetProducts(Map<String, List<String>> paramsMap) {
        return paramsMap.containsKey("id");
    }
}
