public class PracticalMain {
    public static void main(String[] args) throws Exception {
        BigNumber first = new BigNumber("16000");
        BigNumber second = new BigNumber("-97000");

        System.out.println(new BigNumberOperator().add(first, second));
    }
}
