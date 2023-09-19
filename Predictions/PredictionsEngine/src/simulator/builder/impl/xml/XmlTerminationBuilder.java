package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDBySecond;
import resources.jaxb.schema.generated.PRDByTicks;
import resources.jaxb.schema.generated.PRDTermination;
import simulator.builder.api.interfaces.TerminationBuilder;
import simulator.builder.utils.exception.WorldBuilderException;
import simulator.definition.termination.Termination;

import java.util.List;

public class XmlTerminationBuilder implements TerminationBuilder {

    private PRDTermination generatedTermination;

    public XmlTerminationBuilder(PRDTermination genratedTermination) {
        this.generatedTermination = genratedTermination;
    }

    @Override
    public Termination buildTermination() {
        Termination termination;
        Integer ticksTermination = null;
        Integer secondsTermination = null;
        Boolean byUser;

        Object generatedByUser = generatedTermination.getPRDByUser();
        if (generatedByUser != null) {
            byUser = true;
            termination = new Termination(ticksTermination, secondsTermination, byUser);
        }
        else {
            byUser = false;
            termination = buildTeminationInCaseOFTicksOrSeconds(ticksTermination, secondsTermination, byUser);
        }

        return termination;
    }

    private Termination buildTeminationInCaseOFTicksOrSeconds(
            Integer ticksTermination, Integer secondsTermination, Boolean byUser) {

        List<Object> generatedTerminationConditions = generatedTermination.getPRDBySecondOrPRDByTicks();

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

        return new Termination(ticksTermination, secondsTermination, byUser);
    }

}

