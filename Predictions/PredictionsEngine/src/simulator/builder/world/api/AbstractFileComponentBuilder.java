package simulator.builder.world.api;

import java.io.File;

public abstract class AbstractFileComponentBuilder {
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
}
