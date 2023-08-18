package simulator.builder.world.impl.xml;

import resources.jaxb.schema.generated.PRDBySecond;
import resources.jaxb.schema.generated.PRDByTicks;
import resources.jaxb.schema.generated.PRDTermination;
import simulator.builder.world.api.interfaces.TerminationBuilder;
import simulator.builder.world.utils.exception.WorldBuilderException;
import simulator.definition.termination.Termination;

import java.util.List;

public class XmlTerminationBuilder implements TerminationBuilder {

    private PRDTermination generatedTermination;

    public XmlTerminationBuilder(PRDTermination genratedTermination) {
        this.generatedTermination = genratedTermination;
    }

    @Override
    public Termination buildTermination() {
        Termination termination = new Termination();

        List<Object> generatedTerminationConditions = generatedTermination.getPRDByTicksOrPRDBySecond();
        for (int i = 0; i < 2; i++) {
            mapSingleTerminationProcedure(termination, generatedTerminationConditions.get(i));
        }
            return termination;
    }

    private void mapSingleTerminationProcedure (Termination termination, Object generatesTerminationCondition){

        if (generatesTerminationCondition instanceof PRDByTicks) {
            PRDByTicks generatedTicksTermination = (PRDByTicks) generatesTerminationCondition;
            termination.setTicksTermination(generatedTicksTermination.getCount());
        } else if (generatesTerminationCondition instanceof PRDBySecond) {
            PRDBySecond generatedSecondsTermination = (PRDBySecond) generatesTerminationCondition;
            termination.setSecondsTermination(generatedSecondsTermination.getCount());
        } else {
            throw new WorldBuilderException("Can't build termination definition. PRDTermination structure is invalid");
        }
    }
}

