package src;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// PUT YOUR IMPORTS HERE

import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;

public class HiddenSecrets {
    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {
        // Put your code to request a file path,
        // read in a string from System.in,
        // convert that string into A Path type using Paths class,
        // and call the getHiddenSecrets method to get the file's meta-data
        // HERE
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file path here:");
        String line = in.nextLine();
        Path path = Paths.get(line);
        getHiddenSecrets(path.toFile());
        System.out.println();
    }
}
