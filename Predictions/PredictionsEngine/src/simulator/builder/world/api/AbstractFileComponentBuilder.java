package simulator.builder.world.api;

import simulator.builder.world.validator.api.WorldContextBuilderHelper;

public abstract class AbstractFileComponentBuilder extends AbstractComponentBuilder {
    protected String dataSrcFilePath;


    public AbstractFileComponentBuilder(String dataSrcFilePath, WorldContextBuilderHelper contextValidator) {
        super(contextValidator);
        this.dataSrcFilePath = dataSrcFilePath;
    }
}
