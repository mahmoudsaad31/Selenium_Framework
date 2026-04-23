package utils.data_Reader;


import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.report.LogsUtils;

import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonReader;
    String jsonFileName;

    // constructor
    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            // convert json format to string
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    // method to get data from json file as string
    public String getJsonData(String jsonPath) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage(), "No results for json path: '" + jsonPath + "' in the json file: '" + this.jsonFileName + "'");
        }
        LogsUtils.info("Json path: '" + jsonPath + "' in the json file: '" + this.jsonFileName + "' has value: '" + testData + "'");
        return testData;
    }

}
