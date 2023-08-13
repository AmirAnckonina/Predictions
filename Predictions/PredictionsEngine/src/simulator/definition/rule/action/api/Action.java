package simulator.definition.rule.action.api;

import simulator.execution.context.api.Context;

public interface Action {
    void invoke(Context context);
}
