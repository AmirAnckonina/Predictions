package simulator.builder.mainBuilder.impl.xml;

import simulator.builder.componentsBuilder.impl.xml.XmlEntityBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlEnvironemntBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlRuleBuilder;
import simulator.builder.componentsBuilder.impl.xml.XmlTerminationBuilder;
import simulator.builder.mainBuilder.api.AbstractWorldBuilder;
import simulator.builder.mainBuilder.impl.xml.utils.XmlSimulationWorldBuilderUtils;
import definition.world.World;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XmlSimulationWorldBuilder extends AbstractWorldBuilder {
    private File dataSrcFile;
    private World world;
    private XmlSimulationWorldBuilderUtils xmlWorldUtils;
    private final static String JAXB_PACKAGE_NAME = "jaxb.schema.generated";

    public XmlSimulationWorldBuilder() {
        super(new XmlEnvironemntBuilder(), new XmlEntityBuilder(), new XmlRuleBuilder(), new XmlTerminationBuilder());
    }
    public void setDataSrcFile(File dataSrcFile) {
        this.dataSrcFile = dataSrcFile;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public void buildWorld() {
      /*  try {
            InputStream xmlInputStream = new FileInputStream(dataSrcFile);
            World generatedWorld = xmlWorldUtils.deserializeFrom(xmlInputStream, JAXB_PACKAGE_NAME);




        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/

    }

    @Override
    public void buildEnvironment() {

    }

    @Override
    public void buildEntities() {

    }

    @Override
    public void buildRules() {

    }

    @Override
    public void buildTermination() {

    }
}
