package simulator.utils;

import builder.utils.enums.eBuilderDataSrcType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimulatorUtils {
    public File getFileByPath(String filePath) {
            return new File(filePath);
    }

    public eBuilderDataSrcType getDataSrcTypeByFileExtention(String filePath) {

        eBuilderDataSrcType dataSrcType;
        Path path = Paths.get(filePath);
        String fileExtenstion = path.getFileName().toString();
        return eBuilderDataSrcType.valueOf(fileExtenstion);
    }

}
