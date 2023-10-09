package TestNG;

import java.util.HashMap;
import java.util.Map;

public class DataStoreInMap {
    public static Map<String, String> getTestData() {
        Map<String, String> testData = new HashMap<>();
        testData.put("johnsmith", "AnotherPassword");
        testData.put("tomsmith", "SuperSecretPassword!");
        // Add more test data as needed
        return testData;
    }
}
