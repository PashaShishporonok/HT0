import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileInformationCollector {

    private String artistName;
    private String albumName;
    private String trackName;
    private int trackLength;
    private String trackPath;
    private String sum;
    private final String ALGORITHM = "SHA-1";

    public List<Mp3> getMp3 (List<File> fileListToRead) {
        List<Mp3> listOfMp3ToReturn = new ArrayList<Mp3>();
        for (File file : fileListToRead) {
            Mp3File mp3file = null;
            try {
                mp3file = new Mp3File(file);
            } catch (IOException e) {
                System.out.println("File: " + file.getPath() + "can't be read");
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            }
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            artistName = id3v2Tag.getArtist();
            if (artistName == null) {
                artistName = "Unknown Artist";
            }
            albumName = id3v2Tag.getAlbum();
            if (albumName == null) {
                albumName = "Unknown Album";
            }
            trackName = id3v2Tag.getTitle();
            if (trackName == null) {
                trackName = "Unknown Track";
            }
            trackLength = (int) mp3file.getLengthInSeconds();
            trackPath = file.getAbsolutePath();
            sum = calculateSum(file);
            listOfMp3ToReturn.add(new Mp3(artistName, albumName, trackName, trackLength, trackPath, sum));
        }
        return listOfMp3ToReturn;
    }

    public String calculateSum (File inputFile) {
        StringBuilder sb = new StringBuilder();
        try {
            final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            final FileInputStream fis = new FileInputStream(inputFile);
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(dataBytes)) > 0) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();
            for(int i = 0; i < mdBytes.length; i++) {
                sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

}
