package simulator.mainManager.utils;

import simulator.builder.utils.file.enums.DataFileType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public final class SimulatorUtils {
    public static File getFileByPath(String filePath) {
        return new File(filePath);
    }

    public static DataFileType getDataSrcTypeByFileExtention(String filePath) {

        DataFileType dataSrcType;
            Path path = Paths.get(filePath);
            String fileExtenstion = path.getFileName().toString();
            return DataFileType.valueOf(fileExtenstion);
    }

    public static String getGUID(){
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

}
