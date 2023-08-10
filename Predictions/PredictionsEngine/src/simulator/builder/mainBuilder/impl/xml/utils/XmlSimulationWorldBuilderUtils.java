package simulator.builder.mainBuilder.impl.xml.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class XmlSimulationWorldBuilderUtils {
    public PRDworld deserializeFrom(InputStream input, String pkjName) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(pkjName);
        Unmarshaller u = jc.createUnmarshaller();
        return (PRDworld) u.unmarshal(input);
    }
}
