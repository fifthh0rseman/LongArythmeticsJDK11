import java.util.Objects;

public class BigNumber implements Comparable<BigNumber> {
    private final String value;
    private String sign;

    public BigNumber(String value) {
        String sign = value.substring(0, 1);
        if (sign.equals("-") || sign.equals("+")) {
            this.sign = sign;
            this.value = stripLeadingZeros(value.substring(1));
        } else {
            this.sign = "+";
            this.value = stripLeadingZeros(value);
        }
    }

    public String stripLeadingZeros(String value) {
        if (!value.equals("0") && value.charAt(0) == '0') {
            while (value.charAt(0) == '0') {
                value = value.substring(1);
            }
        }
        return value;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigNumber bigNumber = (BigNumber) o;
        return Objects.equals(value, bigNumber.value) && Objects.equals(sign, bigNumber.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, sign);
    }

    @Override
    public String toString() {
        return sign.equals("-") ? sign.concat(value) : value;
    }

    public String getValue() {
        return value;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        if (sign.equals("-") || sign.equals("+")) {
            this.sign = sign;
        }
    }

    @Override
    public int compareTo(BigNumber o) {
        String thisSign = this.getSign();
        String oSign = o.getSign();
        if (thisSign.equals("+") && oSign.equals("-")) {
            return 1;
        } else if (thisSign.equals("-") && oSign.equals("+")) {
            return -1;
        } else if (thisSign.equals("+") && oSign.equals("+")) {
            return compareSameSignNumbers(this, o);
        } else {
            return -1*compareSameSignNumbers(this, o);
        }
    }

    public int compareSameSignNumbers(BigNumber a, BigNumber b) {
        int diff = a.getValue().length() - b.getValue().length();
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        } else {
            for (int i = 0; i < a.getValue().length(); i++) {
                int numberA = a.getValue().charAt(i) - '0';
                int numberB = b.getValue().charAt(i) - '0';
                if (numberA > numberB) {
                    return 1;
                } else if (numberA < numberB) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public BigNumber abs() {
        this.setSign("+");
        return this;
    }


}
