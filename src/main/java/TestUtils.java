public class TestUtils {
    public static Boolean compareDouble(Double d1, Double d2, Integer precision) {
        return Math.abs(d1 - d2) <= Math.pow(10, -precision);
    }
}
