package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDByTicks;
import resources.jaxb.schema.generated.PRDTermination;
import simulator.builder.api.AbstractFileComponentBuilder;
import simulator.builder.api.TerminationBuilder;
import simulator.definition.termination.Termination;

import java.util.List;

public class XmlTerminationBuilder extends AbstractFileComponentBuilder<Termination> implements TerminationBuilder {

    private PRDTermination generatedTermination;

    public XmlTerminationBuilder(PRDTermination genratedTermination) {
        this.generatedTermination = genratedTermination;
    }

    @Override
    public Termination buildTermination() {
        Termination termination = new Termination();

        try {
            List<Object> terminationConditions = generatedTermination.getPRDByTicksOrPRDBySecond();
            if (terminationConditions.get(0) instanceof PRDByTicks) {

            }

            return termination;

        } catch (Exception e) {
            return null;
        }
    }
}
