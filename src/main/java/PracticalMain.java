public class PracticalMain {
    public static void main(String[] args) {
        BigNumber a = new BigNumber("16020");
        BigNumber b = new BigNumber("97000");
        System.out.println(new BigNumberOperator().subtract(b, a));
    }
}
