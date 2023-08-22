package simulator.builder.impl.xml;

import resources.jaxb.schema.generated.PRDAction;
import simulator.builder.api.interfaces.ActionListBuilder;
import simulator.builder.api.abstracts.AbstractComponentBuilder;
import simulator.builder.validator.api.WorldBuilderContextValidator;
import simulator.definition.rule.action.api.abstracts.AbstractAction;

import java.util.ArrayList;
import java.util.List;

public class XmlActionListBuilder extends AbstractComponentBuilder implements ActionListBuilder {

    private List<PRDAction> generatedActions;

    public XmlActionListBuilder(List<PRDAction> generatedActions, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.generatedActions = generatedActions;
    }

    @Override
    public List<AbstractAction> buildActions() {
        List<AbstractAction> actions = new ArrayList<>();
        for (PRDAction genAction: generatedActions) {
            AbstractAction newAction = new XmlActionBuilder(genAction, contextValidator).BuildAction();
            actions.add(newAction);
        }

        return actions;
    }
}
