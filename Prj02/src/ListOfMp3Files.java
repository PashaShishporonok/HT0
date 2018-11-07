import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ListOfMp3Files {

    List<Mp3> inputMp3;
    HashMap<String, HashMap<String, List<Mp3>>> artistMap;


    public ListOfMp3Files(List<Mp3> inputMp3) {

        artistMap = new HashMap<>();
        this.inputMp3 = inputMp3;
        if (inputMp3.size() != 0) {
            for (Mp3 mp3 : inputMp3) {
                putArtist(mp3);

            }
        } else {

        }
    }

    public void putArtist (Mp3 input) {
        HashMap<String, List<Mp3>> newAlbumMap = new HashMap<>();
        if (artistMap.get(input.getArtistName()) == null) {

            putAlbum (input, newAlbumMap);
        } else {

            newAlbumMap = artistMap.get(input.getArtistName());
            putAlbum(input, newAlbumMap);
            artistMap.put(input.getArtistName(), newAlbumMap);
        }
        artistMap.put(input.getArtistName(), newAlbumMap);
    }

    public void putAlbum (Mp3 inputMp3, HashMap<String, List<Mp3>> map) {
        List<Mp3> newListOfMp3 = new ArrayList<>();
        if (map.get(inputMp3.getAlbumName()) == null) {
            newListOfMp3.add(inputMp3);
        } else {
            newListOfMp3 = map.get(inputMp3.getAlbumName());
            newListOfMp3.add(inputMp3);
        }
        map.put(inputMp3.getAlbumName(), newListOfMp3);
    }

    public String getHtmlToWrite () {
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append("<ul>\n");
        Set<String> setOfArtist = artistMap.keySet();
        for (String artist : setOfArtist) {
            stringBuilder.append("\t<li>");
            stringBuilder.append("Artist name: " + artist + "<ul>\n");
            HashMap<String, List<Mp3>> albumsMap = artistMap.get(artist);
            Set<String> albums = albumsMap.keySet();
            for (String album : albums) {
                stringBuilder.append("\t\t<li>");
                stringBuilder.append("Album name: " + album + "<ul>\n");
                List<Mp3> mp3 = albumsMap.get(album);
                for (Mp3 mp3File : mp3) {
                    stringBuilder.append("\t\t\t<li>");
                    stringBuilder.append("Track name: " + mp3File.getTrackName());
                    stringBuilder.append(" Track length: " + mp3File.getTrackLength());
                    stringBuilder.append(" <a href =\"" + mp3File.getTrackPath() + "\">Link to the file</a></li>\n");
                }
                stringBuilder.append("\t\t</ul></li>\n");
            }
            stringBuilder.append("\t</ul></li>\n");
        }
        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }

}
