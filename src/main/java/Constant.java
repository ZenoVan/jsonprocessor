import java.util.Arrays;
import java.util.List;

/**
 * Created by FanZeQiu on 2021/1/19
 */
public class Constant {
    private static final List<String> TEST_TRAIN = Arrays.asList("0", "");
    private static final List<String> TEST_VAL = Arrays.asList("1", "");
    private static final List<String> NEWS_TRAIN = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "17", "19", "11", "12", "13");
    private static final List<String> NEWS_VAL = Arrays.asList("10", "14", "15", "16", "8", "18", "9");
    private static final List<String> AMAZON_TRAIN = Arrays.asList("0", "1", "2", "3", "4", "5", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    private static final List<String> AMAZON_VAL = Arrays.asList("5", "6", "7", "8", "9", "10", "11", "12", "13");
    private static final List<String> HUFFPOST_TRAIN = Arrays.asList("32", "38", "28", "1", "2", "0", "3", "4", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "29", "33", "34", "35", "36", "37", "40");
    private static final List<String> HUFFPOST_VAL = Arrays.asList("39", "24", "8", "19", "5", "7", "6", "20", "21", "22", "23", "25", "26", "27", "30", "31");
    private static final List<String> REUTERS_TRAIN = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19");
    private static final List<String> REUTERS_VAL = Arrays.asList("20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");

    public static List< String > getTestTrain() {
        return TEST_TRAIN;
    }

    public static List< String > getTestVal() {
        return TEST_VAL;
    }

    public static List< String > getNewsTrain() {
        return NEWS_TRAIN;
    }

    public static List< String > getNewsVal() {
        return NEWS_VAL;
    }

    public static List< String > getAmazonTrain() {
        return AMAZON_TRAIN;
    }

    public static List< String > getAmazonVal() {
        return AMAZON_VAL;
    }

    public static List< String > getHuffpostTrain() {
        return HUFFPOST_TRAIN;
    }

    public static List< String > getHuffpostVal() {
        return HUFFPOST_VAL;
    }

    public static List< String > getReutersTrain() {
        return REUTERS_TRAIN;
    }

    public static List< String > getReutersVal() {
        return REUTERS_VAL;
    }
}
