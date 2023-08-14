package simulator.manager.utils;

import simulator.builder.world.utils.enums.eBuilderDataSrcType;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SimulatorUtils {
    public static File getFileByPath(String filePath) {

        return new File(filePath);
    }

    public static eBuilderDataSrcType getDataSrcTypeByFileExtention(String filePath) {

            eBuilderDataSrcType dataSrcType;
            Path path = Paths.get(filePath);
            String fileExtenstion = path.getFileName().toString();
            return eBuilderDataSrcType.valueOf(fileExtenstion);
    }

}
