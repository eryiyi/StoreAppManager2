package com.liangxunwang.unimanager.baidu.channel.transform;


import com.liangxunwang.unimanager.baidu.annotation.HttpParamKeyName;
import com.liangxunwang.unimanager.baidu.annotation.R;
import com.liangxunwang.unimanager.baidu.channel.model.ChannelRequest;
import com.liangxunwang.unimanager.baidu.utility.StringUtility;
import com.liangxunwang.unimanager.baidu.utility.YunCommonUtility;

import java.lang.reflect.Field;
import java.util.*;

public class ChannelRestRequestMapper {

    public Map<String, String> marshall(ChannelRequest request) {

        Map<String, String> params = new TreeMap<String, String>();
        Field[] childField = request.getClass().getDeclaredFields();
        Field[] superFileds = request.getClass().getSuperclass()
                .getDeclaredFields();

        List<Field> fieldList = new LinkedList<Field>();
        fieldList.addAll(Arrays.asList(childField));
        fieldList.addAll(Arrays.asList(superFileds));

        for (Field field : fieldList.toArray(new Field[0])) {
            try {
                field.setAccessible(true);
                if (field.isAnnotationPresent(HttpParamKeyName.class)) {
                    Object obj = field.get(request);
                    if (obj == null) {
                        HttpParamKeyName annotation = field
                                .getAnnotation(HttpParamKeyName.class);
                        if (annotation.param() == R.REQUIRE) {
                            throw new RuntimeException("require set");
                        }
                    } else {
                        HttpParamKeyName annotation = field
                                .getAnnotation(HttpParamKeyName.class);
                        Class zlass = field.getType();
                        if (zlass.equals(Long.class)
                                || "long".equalsIgnoreCase(zlass.getName())) {
                            if (obj == null) { // (Long)obj < 0
                                if (annotation.param() == R.REQUIRE) {
                                    throw new RuntimeException("require set");
                                }
                            } else {
                                params.put(annotation.name(), obj.toString());
                            }
                        } else if (zlass.equals(Integer.class)
                                || "int".equalsIgnoreCase(zlass.getName())) {
                            if (obj == null) { // (Integer)obj < 0
                                if (annotation.param() == R.REQUIRE) {
                                    throw new RuntimeException("require set");
                                }
                            } else {
                                params.put(annotation.name(), obj.toString());
                            }
                        } else if (zlass.equals(String.class)) {
                            params.put(annotation.name(), (String) obj);
                        } else if (zlass.equals(java.util.Date.class)) {
                            params.put(annotation.name(), YunCommonUtility
                                    .formatFromDate((java.util.Date) obj));
                        } else if (zlass.isEnum()) {
                            params.put(annotation.name(), obj.toString());
                        } else if (zlass.equals(List.class)) {
                            params.put(annotation.name(),
                                    StringUtility.toJson((List) obj));
                        } else if (zlass.equals(java.util.Set.class)) {
                            params.put(annotation.name(),
                                    StringUtility.toJson((java.util.Set) obj));
                        } else {
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

}
