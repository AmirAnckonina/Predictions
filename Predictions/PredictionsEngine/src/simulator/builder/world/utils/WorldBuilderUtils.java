package simulator.builder.world.utils;

import simulator.builder.world.utils.enums.eDataFileType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class WorldBuilderUtils {
    public static File getFileByPath(String filePath) {
        return new File(filePath);
    }

    public static eDataFileType getDataFileTypeByFileExtension(String filePath) {

            eDataFileType dataSrcType;
            Path path = Paths.get(filePath);
            String fileExtenstion = path.getFileName().toString();
            return eDataFileType.valueOf(fileExtenstion);
    }

}
