package simulator.builder.api.abstracts;

import simulator.builder.validator.api.WorldBuilderContextValidator;

public class AbstractComponentBuilder {
    protected String componentName;

    protected WorldBuilderContextValidator contextValidator;
    public AbstractComponentBuilder(WorldBuilderContextValidator contextValidator) {
        this.contextValidator = contextValidator;
    }

}
