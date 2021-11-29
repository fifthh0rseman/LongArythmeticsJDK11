import java.util.Arrays;

public class BigNumberOperator {

    public BigNumber add(BigNumber a, BigNumber b) {

        String signA = a.getSign();
        String signB = b.getSign();
        if (signA.equals("+") && signB.equals("+")) {
            return addSameSignNumbers(a, b);
        }

        if (signA.equals("-") && signB.equals("-")) {
            BigNumber result = addSameSignNumbers(a, b);
            result.setSign("-");
            return result;
        }

        if (signA.equals("-") && signB.equals("+")) {
            a.setSign("+");
            return subtract(b, a);
        } else {
            b.setSign("+");
            return subtract(a, b);
        }
    }

    public BigNumber subtract(BigNumber a, BigNumber b) {
        String firstValue = stripValue(a);
        String secondValue = stripValue(b);

        if (b.getSign().equals("-")) {
            b.setSign("+");
            return add(a, b);
        }

        if (a.getSign().equals("-") && b.getSign().equals("+")) {
            BigNumber result = addSameSignNumbers(a, b);
            result.setSign("-");
            return result;
        } else {
            char[] resultArray;
            if (a.compareTo(b) < 0) {
                resultArray = doCalculationForSubtracting(secondValue.toCharArray(), firstValue.toCharArray());
                BigNumber result = new BigNumber(charArrayToString(resultArray));
                result.setSign("-");
                return result;
            } else {
                resultArray = doCalculationForSubtracting(firstValue.toCharArray(), secondValue.toCharArray());
                return new BigNumber(charArrayToString(resultArray));
            }
        }

    }

    private char[] doCalculationForSubtracting(char[] firstValueArray, char[] secondValueArray) {
        secondValueArray = addLeadingZeros(charArrayToString(secondValueArray), firstValueArray.length - secondValueArray.length).toCharArray();
        char[] resultArray = new char[firstValueArray.length];
        int ctr = firstValueArray.length - 1;
        int leadingDozen = 0;
        while (ctr >= 0) {
            int firstNumber = firstValueArray[ctr] - '0';
            int secondNumber = secondValueArray[ctr] - '0';
            int result = firstNumber - secondNumber + leadingDozen;
            if (result < 0) {
                result += 10;
                leadingDozen = -1;
            } else {
                leadingDozen = 0;
            }
            //System.out.println("res: " + result);
            resultArray[ctr] = Character.forDigit(result, 10);
            ctr--;
        }
        return resultArray;
    }

    private BigNumber addSameSignNumbers(BigNumber a, BigNumber b) {
        String firstValue = stripValue(a);
        String secondValue = stripValue(b);

        int diff = firstValue.length() - secondValue.length();

        if (diff < 0) {
            String temp = firstValue;
            firstValue = secondValue;
            secondValue = temp;
        }

        secondValue = addLeadingZeros(secondValue, diff);

        char[] resultArray = doCalculationForAdding(firstValue.toCharArray(), secondValue.toCharArray());

        return new BigNumber(charArrayToString(resultArray));
    }

    private char[] doCalculationForAdding(char[] firstValueArray, char[] secondValueArray) {
        char[] resultArray = new char[firstValueArray.length+1];
        Arrays.fill(resultArray, '0');
        int ctr = firstValueArray.length - 1;
        int leadingDozen = 0;
        while (ctr >= 0) {
            int firstNumber = firstValueArray[ctr] - '0';
            int secondNumber = secondValueArray[ctr] - '0';
            int result = firstNumber + secondNumber + leadingDozen;
            resultArray[ctr+1] = Character.forDigit(result % 10, 10);
            leadingDozen = result / 10;
            ctr--;
        }
        resultArray[0] = Character.forDigit(leadingDozen, 10);
        return resultArray;
    }

    private String charArrayToString(char[] resultArray) {
        StringBuilder builder = new StringBuilder();

        for (char c : resultArray) {
            builder.append(c);
        }

        return builder.toString();
    }

    private String addLeadingZeros(String secondValue, int diff) {
        String leadingZeros = "";
        while (leadingZeros.length() < diff) {
            leadingZeros = leadingZeros.concat("0");
        }

        secondValue = leadingZeros.concat(secondValue);
        return secondValue;
    }

    private String stripValue(BigNumber a) {
        return a.getValue().strip();
    }
}
