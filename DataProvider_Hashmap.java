package TestNG;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class DataProvider_Hashmap {
    @DataProvider(name = "testData")
    public Object[][] getTestData() {
        Map<String, String> dataMap = DataStoreInMap.getTestData();
        Object[][] testData = new Object[dataMap.size()][3];
        int index = 0;
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            testData[index][0] = entry.getKey();
            testData[index][1] = entry.getValue();
            if(entry.getKey().equals("tomsmith") && entry.getValue().equals("SuperSecretPassword!"))
                testData[index][2] = "valid";
            else
                testData[index][2] = "invalid";
            index++;
        }
        return testData;
    }
}
