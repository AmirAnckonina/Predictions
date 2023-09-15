package dto;

public class StringRule {

    private String name;
    private String activationTickInterval;
    private String activationProbability;
    private String numberOfActions;

    public StringRule(String name, String activationTickInterval, String activationProbability, String numberOfActions) {
        this.name = name;
        this.activationTickInterval = activationTickInterval;
        this.activationProbability = activationProbability;
        this.numberOfActions = numberOfActions;
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
