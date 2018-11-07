import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfDuplicateNames {

    private HashMap<String, List<String>> trackMap;
    private static final Logger logger = LogManager.getLogger(ListOfDuplicateNames.class);

    public ListOfDuplicateNames(List<Mp3> inputList) {
        if (inputList != null) {
            trackMap = new HashMap<>();
            for(Mp3 file : inputList) {
                logger.info("Putting mp3 file " + file.getTrackPath() + " to HashMap");
                putMp3ToMap(file, trackMap);
            }
        } else {
            logger.warn("Input List of mp3 files is empty!");
        }
    }

    public void putMp3ToMap (Mp3 inputMp3, HashMap<String, List<String>> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Artist: " + inputMp3.getArtistName() + " Album: " + inputMp3.getAlbumName() + " Track: " + inputMp3.getTrackName());
        String fullNameOfMp3 = stringBuilder.toString();
        logger.info("Creating full name of mp3 file ....Full name is: " + fullNameOfMp3);
        if (inputMp3 != null && map != null) {
            List <String> tempList = new ArrayList<>();
            if (map.get(fullNameOfMp3) == null) {
                logger.info("The key: " + fullNameOfMp3 + " not found, adding key and value to HashMap");
                tempList.add(inputMp3.getTrackPath());
            } else {
                logger.info("The key: " + fullNameOfMp3 + " is found, adding only value to HashMap");
                tempList = map.get(fullNameOfMp3);
                tempList.add(inputMp3.getTrackPath());
            }
            map.put(fullNameOfMp3, tempList);
        }
    }

    public String getHtmlToWrite () {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : trackMap.entrySet()) {
            List<String> tempList = (List<String>)entry.getValue();
            if (tempList.size() > 1) {
                stringBuilder.append("<ul>\n\t<li>\n\t\t" + entry.getKey() + "\n\t\t<ul>\n");
                for (String tempString : tempList) {
                    stringBuilder.append("\t\t\t<li>\n\t\t\t\t" + tempString + "\n\t\t\t</li>\n");
                }
                stringBuilder.append("\t\t</ul>\n\t</li>\n</ul>");
            }
        }
        return stringBuilder.toString();
    }


}
