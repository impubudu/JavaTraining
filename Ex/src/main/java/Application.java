import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\Pubudu\\Downloads\\IFSLabs_BrkTheCode_DataSet.txt"));

            JSONObject jsonObject = (JSONObject) obj;

            String id = (String) jsonObject.get("id");
            System.out.println(id);

            String generatedOn = (String) jsonObject.get("generatedOn");
            System.out.println(generatedOn);

            List<Long> dataList = (List<Long>) jsonObject.get("data");
            System.out.println(dataList);

            List<Long> results = new ArrayList<Long>();
            Long previousDataValue = 0L;
            Long previousResultValue = 1L;
            int previousCount = 0;
            for (Long data : dataList) {
                if (results.isEmpty()) {
                    results.add(previousResultValue);
                    previousDataValue = data;
                    previousCount = previousCount + 1;
                } else if (data > previousDataValue) {
                    previousResultValue = previousResultValue + 1;
                    results.add(previousResultValue);
                    previousDataValue = data;
                    previousCount = 1;
                } else if (data < previousDataValue) {
                    if (previousResultValue == 1L) {
                        for (int i = 0; i < previousCount; i++) {
                            Long value = results.get(results.size() - (i + 1));
                            if (i>0 && value > results.get(results.size() - (i ))){
                                value = value;
                            } else {
                                value = value + 1;
                            }
                            results.set(results.size() - (i + 1), value);
                        }
                    }
                    previousResultValue = 1L;
                    results.add(previousResultValue);
                    previousDataValue = data;
                    previousCount = previousCount + 1;
                } else {
                    previousResultValue = 1L;
                    results.add(previousResultValue);
                    previousDataValue = data;
                    previousCount = 1;
                }
            }

            System.out.println("result : " + results);
            FileWriter file = new FileWriter("C:\\Users\\Pubudu\\Downloads\\result.txt");
            JSONObject resultObj = new JSONObject();
            resultObj.put("answer", results);
            file.write(resultObj.toJSONString());
            file.flush();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}