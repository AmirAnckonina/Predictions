package simulator.builder.api.abstracts;

import simulator.builder.validator.api.WorldBuilderContextValidator;

import java.io.InputStream;


public abstract class AbstractFileComponentBuilder extends AbstractComponentBuilder {
    protected InputStream xmlFile;


    public AbstractFileComponentBuilder(InputStream xmlFile, WorldBuilderContextValidator contextValidator) {
        super(contextValidator);
        this.xmlFile = xmlFile;
    }
}
