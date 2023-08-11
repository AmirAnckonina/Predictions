package simulator.builder.api;

import java.io.File;

public abstract class AbstractFileComponentBuilder<T> {
    protected T component;
    protected File dataSrcFile;

    public AbstractFileComponentBuilder() {
    }

    public AbstractFileComponentBuilder(File dataSrcFile) {
        this();
        this.dataSrcFile = dataSrcFile;
    }

    public void setDataSrcFile(File dataSrcFile) {
        this.dataSrcFile = dataSrcFile;
    }

    public T getComponent() {
        return component;
    }
}
