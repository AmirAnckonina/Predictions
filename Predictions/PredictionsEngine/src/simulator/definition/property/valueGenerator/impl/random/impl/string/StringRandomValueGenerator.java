package simulator.definition.property.valueGenerator.impl.random.impl.string;

import simulator.definition.property.valueGenerator.impl.random.api.BaseRandomValueGenerator;

public class StringRandomValueGenerator extends BaseRandomValueGenerator<String> {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?,_-().";
    private static final int MAX_LENGTH = 50;

    @Override
    public String generateValue() {
        StringBuilder randomString = new StringBuilder();
        int length = random.nextInt(MAX_LENGTH);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
