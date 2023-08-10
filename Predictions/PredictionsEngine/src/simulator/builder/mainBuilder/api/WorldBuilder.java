package builder.mainBuilder.api;

import definition.world.World;

public interface WorldBuilder {
    World getWorld();
    void buildWorld();
    void buildEnvironment();
    void buildEntities();
    void buildRules();
    void buildTermination();
}
