package simulator.builder.mainBuilder.impl.xml.utils;

import resources.jaxb.schema.generated.*;
import simulator.definition.entity.Entity;
import simulator.definition.environment.Environment;
import simulator.definition.rule.Rule;
import simulator.definition.termination.Termination;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class XmlWorldBuilderUtils {
    public <T> T deserializeFrom(InputStream input, String pkjName) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(pkjName);
        Unmarshaller u = jc.createUnmarshaller();
        return (T) u.unmarshal(input);
    }

    public <T> T getGeneratedClassFromFile(File dataSrcFile, String generatedPkjName) throws FileNotFoundException, JAXBException {

        InputStream xmlInputStream = new FileInputStream(dataSrcFile);
        return deserializeFrom(xmlInputStream, generatedPkjName);
    }

    public void MapEnvironment(PRDEvironment generatedEnv, Environment environment) {
        for (PRDEnvProperty envProp : generatedEnv.getPRDEnvProperty()) {

        }
    }

    public void MapEntities(PRDEntities generatedEntities, List<Entity> entities) {

    }

    public void MapRules(PRDRules generatedRules, List<Rule> rules) {
    }

    public void MapTermination(PRDTermination generatedTermination, Termination termination) {
    }
}
