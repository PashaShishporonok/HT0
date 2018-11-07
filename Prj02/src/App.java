import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

        final String OUTPUT_PATH = "f:/my.html";

        if (args.length != 0) {
            FileListCreator fileCreator = new FileListCreator(args);
            List<File> listOfFiles = fileCreator.getMp3Files();
            FileInformationCollector collector = new FileInformationCollector();
            List<Mp3> mp3List = collector.getMp3(listOfFiles);
            ListOfMp3Files listTask1 = new ListOfMp3Files(mp3List);
            ListOfDuplicateNames listTask2 = new ListOfDuplicateNames(mp3List);
            ListOfDuplicateSum listTask3 = new ListOfDuplicateSum(mp3List);

            HtmlFileWriter writer = new HtmlFileWriter(OUTPUT_PATH);
            writer.write(listTask1.getHtmlToWrite());
            writer.write(listTask2.getHtmlToWrite());
            writer.write(listTask3.getHtmlToWrite());
        }
    }
}