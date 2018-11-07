import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlFileWriter {

    private File outputFile;
    private BufferedWriter writer;
    private boolean canWriteToFile;

    public HtmlFileWriter(String pathToOutputFile) {
        outputFile = new File (pathToOutputFile);
        if (outputFile.exists()) {
            canWriteToFile = false;
            System.out.println("File \"" + pathToOutputFile + "\" already exists please rename output file!");
        } else {
            canWriteToFile = true;
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write (String string) throws IOException {
        if (canWriteToFile) {
            writer = new BufferedWriter(new FileWriter(outputFile, true));
            writer.write(string);
            writer.close();
        }
    }

}
