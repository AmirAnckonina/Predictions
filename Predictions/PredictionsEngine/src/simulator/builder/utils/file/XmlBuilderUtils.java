package simulator.builder.utils.file;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class XmlBuilderUtils {
    private XmlBuilderUtils() {
    }

    public static <T> T deserializeFrom(InputStream input, String pkjName) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(pkjName);
        Unmarshaller u = jc.createUnmarshaller();
        return (T) u.unmarshal(input);
    }

    public static <T> T getGeneratedClassFromFile(File dataSrcFile, String generatedPkjName) throws FileNotFoundException, JAXBException {

        InputStream xmlInputStream = new FileInputStream(dataSrcFile);
        return deserializeFrom(xmlInputStream, generatedPkjName);
    }

}
