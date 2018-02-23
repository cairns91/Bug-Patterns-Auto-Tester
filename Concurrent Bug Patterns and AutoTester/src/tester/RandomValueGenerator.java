package tester;

import java.util.Random;

public class RandomValueGenerator {

    public RandomValueGenerator() {
    }
    
    /**
     * Generates a random lowercase string with a length between 0 and the maximum length
     * @param maxLength
     */
    public static String generateLowercaseString(int maxLength) {
        int stringLength = generateInt(0, maxLength);
        String randomString = "";
        for (int i=0; i < stringLength; i++) {
            randomString += Character.toString((char) generateInt(97, 122));
        }
        
        return randomString;
    }
    
    /**
     * Generates a random uppercase string with a length between 0 and the maximum length
     * @param maxLength
     */
    public static String generateUppercaseString(int maxLength) {
        int stringLength = generateInt(0, maxLength);
        String randomString = "";
        for (int i=0; i < stringLength; i++) {
            randomString += Character.toString((char) generateInt(65, 90));
        }
        
        return randomString;
    }
    
    /**
     * Generates a random double between the minValue and maxValue inclusive
     * @param minValue
     * @param maxValue
     */
    public static double generateDouble(double minValue, double maxValue) {
        return  minValue + (maxValue - minValue) * new Random().nextDouble();
    }
    
    /**
     * Generates a random integer between the minValue and maxValue inclusive
     * @param minValue
     * @param maxValue
     */
    public static int generateInt(int minValue, int maxValue) {
        return new Random().nextInt(maxValue - minValue + 1) + minValue;
    }
  
}
