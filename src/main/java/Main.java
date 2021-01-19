import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FanZeQiu on 2021/1/18
 */
public class Main {

    private static final String PATH = "src/main/resources";
    private static final String[] FILE_NAMES = new String[]{"test.json", "news.json", "amazon.json", "huffpost.json", "reuters.json"};
//    private static final String[] FILE_NAMES = new String[]{"test.json", "news.json"};

    public static void main(String[] args) throws Exception{

        JsonProcessor processor = new JsonProcessor();
        Constant constant = new Constant();
        processor.setPath(PATH);
        for (String fileName : FILE_NAMES) {
            processor.setFileName(fileName);
            String base = "get" + fileName.substring(0, 1).toUpperCase()  + fileName.substring(1, fileName.length() - 5);
            Class clazz = constant.getClass();
            Method getTrain = clazz.getDeclaredMethod(  base + "Train"), getVal = clazz.getDeclaredMethod(base + "Val");
            processor.setTrainLabels((List< String >) getTrain.invoke(constant));
            processor.setValLabels((List< String >) getVal.invoke(constant));
            processor.output(processor.process(processor.input()));
        }

    }

}

