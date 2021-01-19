import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

/**
 * Created by FanZeQiu on 2021/1/18
 */
public class JsonProcessor {

    private String path;

    private String fileName;

    private List<String> trainLabels;

    private List<String> valLabels;

    public JSONObject process(JSONArray input) {
        JSONObject output = new JSONObject();
        JSONObject train = new JSONObject(), val = new JSONObject(), temp;
        for (int i = 0; i < input.size(); i++) {
            JSONObject item = input.getJSONObject(i);
            List<String> text = (List<String>) item.get("text");
            String label = item.getString("label");
            if (trainLabels.contains(label)) {
                temp = train;
            } else {
                temp = val;
            }
            JSONArray array;
            if (!temp.containsKey(label)) {
                array = new JSONArray();
            } else {
                array = temp.getJSONArray(label);
            }
            array.add(generateNewItem(text));
            temp.put(label, array);
        }
        output.put("train", train);
        output.put("val", val);
        return output;
    }

    private JSONObject generateNewItem(List<String> text) {
        JSONObject item = new JSONObject();
        item.put("tokens", JSONArray.parseArray(text.toString()));

        int hIndex = (int)(Math.random()*(text.size() - 1)), tIndex = (int)(Math.random()*(text.size() - 1));
        JSONArray hArray = new JSONArray(), tArray = new JSONArray();
        parseArray(text, hIndex, hArray);
        item.put("h", hArray);

        parseArray(text, tIndex, tArray);
        item.put("t", tArray);
        return item;
    }

    private void parseArray(List<String> text, int index, JSONArray array) {
        array.add(text.get(index));
        array.add("Q" + index);
        JSONArray tOne = new JSONArray();
        JSONArray tTwo = new JSONArray();
        tTwo.add(index);
        tOne.add(tTwo);
        array.add(tOne);
    }

    public JSONArray input() throws Exception{
        File file = new File(path + "/" + fileName);
        String text = FileUtils.readFileToString(file);
        return JSON.parseArray(text);
    }

    @Deprecated
    public JSONArray input(String fileName) throws Exception{
        File file = new File(path + "/" + fileName);
        String text = FileUtils.readFileToString(file);
        return JSON.parseArray(text);
    }

    @Deprecated
    public JSONArray input(String path, String fileName) throws Exception{
        File file = new File(path + "/" + fileName);
        String text = FileUtils.readFileToString(file);
        return JSON.parseArray(text);
    }

    public void output(JSONObject json) {
        JSONObject train = json.getJSONObject("train"), val = json.getJSONObject("val");
        saveJson(train, path + "/output/train-" + fileName);
        saveJson(val, path + "/output/val-" + fileName);
    }

    public void saveJson(JSON json, String filePath){
        String writeString = JSON.toJSONString(json, SerializerFeature.PrettyFormat);

//        System.out.println(writeString);
        BufferedWriter writer = null;
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //如果文件不存在则新建
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        //如果多次执行同一个流程，会导致json文件内容不断追加，在写入之前清空文件
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
            writer.write("");
            writer.write(writeString);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List< String > getTrainLabels() {
        return trainLabels;
    }

    public void setTrainLabels(List< String > trainLabels) {
        this.trainLabels = trainLabels;
    }

    public List< String > getValLabels() {
        return valLabels;
    }

    public void setValLabels(List< String > valLabels) {
        this.valLabels = valLabels;
    }
}
