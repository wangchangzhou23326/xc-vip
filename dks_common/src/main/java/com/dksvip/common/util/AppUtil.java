package com.dksvip.common.util;

import cn.hutool.core.lang.Validator;
import cn.hutool.json.JSONObject;
import com.dksvip.common.exception.AppException;

import java.util.Collections;
import java.util.List;


public class AppUtil {



    public static JSONObject objToJson(Object obj) {
        if(Validator.isNull(obj)){
            throw new AppException("obj is null");
        }
        return (JSONObject) obj;
    }


    public static <T extends Comparable<T>> boolean listCompare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    public static <T> List<T>makeList(T element, int repeatNum){
        if(repeatNum <= 0){
            return null;
        }
        return (List<T>) Collections.nCopies(repeatNum, element);

    }

}
