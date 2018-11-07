import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfDuplicateSum {

    private HashMap<String, List<String>> trackMap;
    private static final Logger logger = LogManager.getLogger(ListOfDuplicateNames.class);

    public ListOfDuplicateSum(List<Mp3> inputList) {
        if (inputList != null) {
            trackMap = new HashMap<>();
            for(Mp3 file : inputList) {
                logger.info("Putting mp3 file " + file.getTrackPath() + " to HashMap");
                putMp3ToMap(file, trackMap);
            }
        } else {
            logger.warn("Input list of mp3 files is empty!");
        }
    }

    public void putMp3ToMap (Mp3 inputMp3, HashMap<String, List<String>> map) {
        if (inputMp3 != null && map != null) {
            List <String> tempList = new ArrayList<>();
            if (map.get(inputMp3.getSum()) == null) {
                logger.warn("The key: " + inputMp3.getSum() + " not found, adding key and value to HashMap");
                tempList.add(inputMp3.getTrackPath());
            } else {
                logger.info("The key: " + inputMp3.getSum() + " is found, adding value to HashMap");
                tempList = map.get(inputMp3.getSum());
                tempList.add(inputMp3.getTrackPath());
            }
            map.put(inputMp3.getSum(), tempList);
        }
    }

    public String getHtmlToWrite () {
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 1;
        for (Map.Entry entry : trackMap.entrySet()) {
            List<String> tempList = (List<String>)entry.getValue();
            if (tempList.size() > 1) { //если есть дубликаты
                stringBuilder.append("<ul>\n\t<li>\n\t\t" + " Duplicate: " + counter + "\n\t\t<ul>\n");
                for (String tempString : tempList) {
                    stringBuilder.append("\t\t\t<li>\n\t\t\t\t" + tempString + "\n\t\t\t</li>\n");
                }
                stringBuilder.append("\t\t</ul>\n\t</li>\n</ul>");
                counter++;
            }
        }
        return stringBuilder.toString();
    }

}
