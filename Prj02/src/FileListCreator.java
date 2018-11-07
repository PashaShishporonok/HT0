import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListCreator {

    private List<File> mp3Files;
    private File rootDirectory;

    public FileListCreator(String[] paths) {
        if (paths.length != 0) {
            mp3Files = new ArrayList<>();
            for (String path : paths) {
                rootDirectory = new File (path);
                generateListOfMp3Files(rootDirectory);
            }
        } else {
            System.out.println("enter directories with mp3 files!");
        }

    }

    public void generateListOfMp3Files (File directory) {
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (File file : filelist) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".mp3")) {
                        mp3Files.add(file);
                    }
                } else if (file.isDirectory()) {
                    generateListOfMp3Files(file);
                }
            }
        } else if (directory.isFile()) {
            if(directory.getName().endsWith(".mp3")) {
                mp3Files.add(directory);
            }
        }
    }

    public List<File> getMp3Files() {
        return mp3Files;
    }

}
