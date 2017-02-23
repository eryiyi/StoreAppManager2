package com.liangxunwang.unimanager.util;

import com.liangxunwang.unimanager.model.College;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

/**
 * Created by zhl on 2015/3/4.
 */
public class MyComparator implements Comparator<College> {

    //关于Collator。
    private Collator collator = Collator.getInstance();//点击查看中文api详解

    public MyComparator() {
    }

    @Override
    public int compare(College college, College t1) {
        //把字符串转换为一系列比特，它们可以以比特形式与 CollationKeys 相比较
        CollationKey key1=collator.getCollationKey(college.getName().toString());//要想不区分大小写进行比较用o1.toString().toLowerCase()
        CollationKey key2=collator.getCollationKey(t1.getName().toString());
        return key1.compareTo(key2);//返回的分别为1,0,-1 分别代表大于，等于，小于。要想按照字母降序排序的话 加个“-”号
    }
}
