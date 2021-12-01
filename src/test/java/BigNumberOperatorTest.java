import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class BigNumberOperatorTest {

    private BigNumber a, b, exp, act;
    private final BigNumberOperator operator = new BigNumberOperator();

    @Test
    public void addSameSign() {
        a = new BigNumber("16020");
        b = new BigNumber("97000");
        act = operator.add(a, b);
        exp = new BigNumber("113020");
        assertEquals(exp, act);
    }

    @Test
    public void subtractSameSign() {
        a = new BigNumber("16020");
        b = new BigNumber("97000");
        act = operator.subtract(a, b);
        exp = new BigNumber("-80980");
        assertEquals(exp, act);
    }

    @Test
    public void addDiffSign() {
        a = new BigNumber("16020");
        b = new BigNumber("-97000");
        act = operator.add(a, b);
        exp = new BigNumber("-80980");
        assertEquals(exp, act);
    }

    @Test
    public void subtractSmallerFromBigger() {
        a = new BigNumber("16020");
        b = new BigNumber("97000");
        act = operator.subtract(b, a);
        exp = new BigNumber("80980");
        assertEquals(exp, act);
    }

    @Test
    public void subtractDiffSign() {
        a = new BigNumber("16020");
        b = new BigNumber("-97000");
        act = operator.subtract(a, b);
        exp = new BigNumber("113020");
        assertEquals(exp, act);
    }
}