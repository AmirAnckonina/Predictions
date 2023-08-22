package simulator.builder.api.interfaces;

import simulator.definition.rule.action.api.abstracts.AbstractAction;

import java.util.List;

public interface ActionListBuilder {
    List<AbstractAction> buildActions();
}
