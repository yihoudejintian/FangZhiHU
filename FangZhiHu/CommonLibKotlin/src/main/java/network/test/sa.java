package network.test;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tongqinyuan on 2018/3/7.
 */

public class sa {

    public void ss(){
        Map<String,Object> header = new HashMap<>();
        header.put("name","张三");


        if(header != null && header.size() > 0){
            Set<String> strings = header.keySet();
            for (String item : strings){
                Object o = header.get(item);
                Log.e("test",item+"==key==value=="+o);
            }
        }

    }
}
