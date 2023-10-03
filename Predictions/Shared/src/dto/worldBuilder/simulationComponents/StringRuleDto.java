package dto.worldBuilder.simulationComponents;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRuleDto {

    private String name;
    private String activationTickInterval;
    private String activationProbability;
    private String numberOfActions;
    private List<StringActionDto> actions;
    private String fullRule;

    public StringRuleDto(String name, String activationTickInterval, String activationProbability, String numberOfActions,
                         List<StringActionDto> actions, String fullRule) {
        this.name = name;
        this.activationTickInterval = activationTickInterval;
        this.activationProbability = activationProbability;
        this.numberOfActions = numberOfActions;
        this.actions = actions;
        this.fullRule = fullRule;
    }

    public StringRuleDto(String fullRule) {
        parseStringRuleToDetailedRule(fullRule);
    }

    private void parseStringRuleToDetailedRule(String fullRule) {
        String name = null;
        String activationTickInterval = null;
        String activationProbability = null;
        String numberOfActions = null;

        // Regular expressions for extracting rule details
        Pattern namePattern = Pattern.compile("rule name: ([^\\r\\n]+)");
        Pattern activationPattern = Pattern.compile("Activation\\{ticksInterval=(\\d+), probability=([0-9.]+)\\}");
        Pattern numberOfActionsPattern = Pattern.compile("Number of actions under rule: (\\d+)");

        Matcher nameMatcher = namePattern.matcher(fullRule);
        Matcher activationMatcher = activationPattern.matcher(fullRule);
        Matcher numberOfActionsMatcher = numberOfActionsPattern.matcher(fullRule);

        if (nameMatcher.find()) {
            name = nameMatcher.group(1);
        }

        if (activationMatcher.find()) {
            activationTickInterval = activationMatcher.group(1);
            activationProbability = activationMatcher.group(2);
        }

        if (numberOfActionsMatcher.find()) {
            numberOfActions = numberOfActionsMatcher.group(1);
        }
        this.name = name;
        this.numberOfActions = numberOfActions;
        this.activationProbability = activationProbability;
        this.activationTickInterval = activationTickInterval;
        this.fullRule = fullRule;
        parseRuleToActions(fullRule);
    }

    private void parseRuleToActions(String fullRule) {
        this.actions = new LinkedList<>();
        String[] fullNameLinesArray = fullRule.split("\r\n");
        for(String line:fullNameLinesArray){
            if(!line.startsWith("rule name") && !line.startsWith("Activation") && !line.startsWith("Number of") &&
                    !line.startsWith("\r") && !line.startsWith("\n")){
                this.actions.add(new StringActionDto(line));
            }
        }
    }

    public List<StringActionDto> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivationTickInterval() {
        return activationTickInterval;
    }

    public void setActivationTickInterval(String activationTickInterval) {
        this.activationTickInterval = activationTickInterval;
    }

    public String getActivationProbability() {
        return activationProbability;
    }

    public void setActivationProbability(String activationProbability) {
        this.activationProbability = activationProbability;
    }

    public String getNumberOfActions() {
        return numberOfActions;
    }

    public void setNumberOfActions(String numberOfActions) {
        this.numberOfActions = numberOfActions;
    }
}
