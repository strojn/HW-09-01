package org.example.Дешифратор;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String filePath = "Test.txt";
        String newString = " Java ";
        ArrayList<String> oldStrings = new ArrayList<>();
        oldStrings.add(" в ");
        oldStrings.add(" у ");

        File fileToBeModified = new File(filePath);

        //refresh Test.txt
        File fileTestExample = new File("Test_example.txt");
        Files.copy(fileTestExample.toPath(), fileToBeModified.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }
            System.out.println(oldContent);

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldStrings.get(0), newString);
            for (int i = 1; i < oldStrings.size(); i++) {
                newContent = newContent.replaceAll(oldStrings.get(i), newString);
            }
            System.out.println(newContent);

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
