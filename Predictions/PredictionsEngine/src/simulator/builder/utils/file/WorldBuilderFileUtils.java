package simulator.builder.utils.file;

import simulator.builder.utils.exception.WorldBuilderManagerException;
import simulator.builder.utils.file.enums.DataFileType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class WorldBuilderFileUtils {
    public static File getFileByPath(String filePath) {
        return new File(filePath);
    }

    public static DataFileType getDataFileTypeByFileExtension(String filePath) {

            DataFileType dataSrcType;
            Path path = Paths.get(filePath);
            String fileExtenstion = extractFileType(path.getFileName().toString());
            return DataFileType.valueOf(fileExtenstion.toUpperCase());
    }

    public static String extractFileType(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        } else {
            throw new WorldBuilderManagerException("File type could be recognized.");
        }
    }
}
