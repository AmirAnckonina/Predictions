package simulator.builder.utils.file;

import simulator.builder.utils.exception.WorldBuilderException;
import simulator.builder.utils.file.enums.eDataFileType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class WorldBuilderFileUtils {
    public static File getFileByPath(String filePath) {
        return new File(filePath);
    }

    public static eDataFileType getDataFileTypeByFileExtension(String filePath) {

            eDataFileType dataSrcType;
            Path path = Paths.get(filePath);
            String fileExtenstion = extractFileType(path.getFileName().toString());
            return eDataFileType.valueOf(fileExtenstion.toUpperCase());
    }

    public static String extractFileType(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        } else {
            throw new WorldBuilderException("File type could be recognized.");
        }
    }
}
