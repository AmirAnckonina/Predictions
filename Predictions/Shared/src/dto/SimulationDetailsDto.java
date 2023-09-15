package dto;

import java.util.List;
import java.util.Map;

public class SimulationDetailsDto {
    private String entitiesInfo;
    private List<String> rulesActions;
    private String terminationInfo;
    private Map<String, StringRule> ruleMap;

    public SimulationDetailsDto(String entitiesInfo, List<String> rulesActions, String terminationInfo) {
        this.entitiesInfo = entitiesInfo;
        this.rulesActions = rulesActions;
        this.terminationInfo = terminationInfo;
        StringRule test = new StringRule(rulesActions.get(0));

//        rule name: r1
//        Activation{ticksInterval=1, probability=0.4}
//        Number of actions under rule: 4
//        Increase{by=3.0, propertyName='p1', type=INCREASE, primaryEntityName='ent-1', actionSecondaryEntityDefinition= - }
//        Decrease{by=environmentPropertyName='e3, propertyName='p2', type=DECREASE, primaryEntityName='ent-1', actionSecondaryEntityDefinition= - }
//            Multiply{arg1=ent-1 :p1, arg2=environmentPropertyName='e1, propertyName='p1', type=MULTIPLY, primaryEntityName='ent-1', actionSecondaryEntityDefinition= - }
//                Divide{arg1=environmentPropertyName='e3, arg2=3.2, propertyName='p2', type=DIVIDE, primaryEntityName='ent-1', actionSecondaryEntityDefinition= - }
    }

    public String getEntitiesInfo() {
        return entitiesInfo;
    }

    public List<String> getRulesActions() {
        return rulesActions;
    }

    public String getTerminationInfo() {
        return terminationInfo;
    }

    public String getInfo() {
        return entitiesInfo + rulesActions + terminationInfo;
    }
}
