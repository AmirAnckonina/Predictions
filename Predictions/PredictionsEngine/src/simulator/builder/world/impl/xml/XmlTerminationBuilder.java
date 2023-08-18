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

        Integer ticksTermination = null;
        Integer secondsTermination = null;

        List<Object> generatedTerminationConditions = generatedTermination.getPRDByTicksOrPRDBySecond();
        int numOfTerminationConditions = generatedTerminationConditions.size();
        if (numOfTerminationConditions == 0) {
            throw new WorldBuilderException("Termination prvoided doesn't contain any condition.");
        }

        for (int i = 0; i < numOfTerminationConditions; i++) {

            if (generatedTerminationConditions.get(i) instanceof PRDByTicks) {
                PRDByTicks generatedTicksTermination = (PRDByTicks) generatedTerminationConditions.get(i);
                ticksTermination = generatedTicksTermination.getCount();
            } else if (generatedTerminationConditions.get(i) instanceof PRDBySecond) {
                PRDBySecond generatedSecondsTermination = (PRDBySecond) generatedTerminationConditions.get(i);
                secondsTermination = generatedSecondsTermination.getCount();
            } else {
                throw new WorldBuilderException(
                        "Can't build termination definition. PRDTermination structure is invalid");
            }
        }

        return new Termination(ticksTermination, secondsTermination);
    }
}

