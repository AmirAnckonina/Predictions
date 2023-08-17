package simulator.builder.world.api;

import simulator.builder.world.validator.api.WorldBuilderContextValidator;

public abstract class AbstractFileComponentBuilder extends AbstractComponentBuilder {
    protected String dataSrcFilePath;


    public AbstractFileComponentBuilder(String dataSrcFilePath, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.dataSrcFilePath = dataSrcFilePath;
    }
}
