package com.dolee.context;

import com.sun.jmx.snmp.ThreadContext;

import java.util.HashMap;
import java.util.Map;

public class Thread_Context {

    private static Thread_Context threadContext = null;
    public   ThreadLocal<String> threadLocalString = new ThreadLocal<String>();
    public  ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>();

    public   Map<String ,Object> cache=new HashMap<>();;
    private Thread_Context() {
    }

    public  static  synchronized    Thread_Context getContext() {
        if (threadContext == null) {
            threadContext = new Thread_Context();

        }
        return threadContext;

    }

}
