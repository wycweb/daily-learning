package com.wangyichao.bigdata.flink07;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map.Entry;

public class MetricBeatFormatUtil {

    /**
     * 获取扁平化json数据
     *
     * @param value
     * @return
     */
    public static JsonObject getFlattenObject(String value) {
        JsonParser parser = new JsonParser();
        JsonElement tree = parser.parse(value);
        JsonObject jo = (JsonObject) tree;
        JsonObject fa = new JsonObject();
        String father = "";
        JsonObject resultJo = flatten(jo, fa, father);

        return resultJo;
    }

    /**
     * 多层json扁平化处理
     *
     * @param object
     * @param flattened
     * @param father
     * @return
     */
    private static JsonObject flatten(JsonObject object, JsonObject flattened, String father) {

        if (flattened == null) {
            flattened = new JsonObject();
        }
        for (Entry<String, JsonElement> entry : object.entrySet()) {
            String midFather = entry.getKey();
            String tmp = father;
            JsonElement tmpVa = (JsonElement) entry.getValue();
            try {
                if (tmpVa.isJsonObject()) {

                    //检测到多层json的时候进行递归处理
                    tmp = tmp + "_" + midFather;//当前层键与之前外层键进行拼接

                    flatten(object.getAsJsonObject(entry.getKey()), flattened, tmp);
                } else {
                    //当前层的值没有嵌套json键值对，直接将键值对添加到flattened中

                    String nowKeyTmp = father + "_" + entry.getKey();
                    String nowKey = nowKeyTmp.substring(1);
                    flattened.add(nowKey, (entry.getValue()));
                }
            } catch (JsonIOException e) {
                e.printStackTrace();
            }
        }

        return flattened;
    }
}
