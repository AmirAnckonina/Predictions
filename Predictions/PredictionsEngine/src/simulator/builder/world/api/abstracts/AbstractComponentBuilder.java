package simulator.builder.world.api.abstracts;

import simulator.builder.world.validator.api.WorldBuilderContextValidator;

public class AbstractComponentBuilder {
    protected WorldBuilderContextValidator contextValidator;
    public AbstractComponentBuilder(WorldBuilderContextValidator contextValidator) {
        this.contextValidator = contextValidator;
    }
}
