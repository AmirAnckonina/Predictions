package simulator.builder.world.api;

import simulator.builder.world.validator.api.WorldContextBuilderHelper;

public class AbstractComponentBuilder {
    protected WorldContextBuilderHelper contextValidator;
    public AbstractComponentBuilder(WorldContextBuilderHelper contextValidator) {
        this.contextValidator = contextValidator;
    }
}
