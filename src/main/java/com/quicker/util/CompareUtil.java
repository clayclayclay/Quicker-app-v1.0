package com.quicker.util;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
public class CompareUtil implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        Date begin = (Date)o1;
        Date end = (Date)o2;
        if (begin.after(end)) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
