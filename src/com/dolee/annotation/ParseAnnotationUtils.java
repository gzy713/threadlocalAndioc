package com.dolee.annotation;

import com.dolee.context.Thread_Context;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class ParseAnnotationUtils {



    public   static void reFeactClass( List<Class> listclasses ) throws Exception {
        Map<String, Object> cache = Thread_Context.getContext().cache;


        for (Class listclass : listclasses) {

            Field[] declaredFields = listclass.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                boolean annotationPresent = declaredField.isAnnotationPresent(Diyautowire.class);

                if(annotationPresent){
                    declaredField.setAccessible(true);
                    getObject(listclass,  declaredField.getType());
                    declaredField.set(cache.get(listclass.getName()),cache.get(declaredField.getType().toString().replace("class ","")) );
                }


            }

        }

    }

    private static void getObject(Class listclass, Class aClass) throws Exception {
        Map<String, Object> cache = Thread_Context.getContext().cache;
        Object o = cache.get(listclass.getName());
        Object o1 = cache.get(aClass.getName());
        if(o==null){
            cache.put(listclass.getName(),listclass.newInstance());
        }
        if(o1==null){
            Object o2 = aClass.newInstance();

            cache.put(aClass.getName(),o2);
           // System.out.println(aClass.getName()+"/"+o2);
        }

    }


    public   static void  preseAnno( ClassLoader loader) throws Exception {

     //   Class<? extends ClassLoader> aClass = loader.getClass();

        final Enumeration<URL> resources = loader.getResources("");
        List<Class> listclasses=new ArrayList<>();

        while (resources.hasMoreElements()){
            URL url = resources.nextElement();


            if(url.getProtocol().equals("file")){
                loaderFile( url.getPath(),listclasses,loader);
            }
        }

       reFeactClass(listclasses);


    }
//    /F:/OpenSource/jvm/out/production/jvm/
    private static void loaderFile(String path,List<Class> listclasses,ClassLoader loader) throws Exception {
        File file = new File(path);

        if (file!=null&& file.getName().contains(".class")){
            //System.out.println(file.getAbsolutePath());
            /*
            * F:\OpenSource\jvm\out\production\jvm\com\dolee\annotation\Diyautowire.class
                F:\OpenSource\jvm\out\production\jvm\com\dolee\annotation\ParseAnnotationUtils.class
                F:\OpenSource\jvm\out\production\jvm\com\dolee\context\Thread_Context.class
                F:\OpenSource\jvm\out\production\jvm\com\dolee\ThreadDemo\Demo.class
                F:\OpenSource\jvm\out\production\jvm\com\dolee\ThreadDemo\DemoArgs.class
                F:\OpenSource\jvm\out\production\jvm\com\dolee\ThreadDemo\DemoArgsThree.class
                F:\OpenSource\jvm\out\production\jvm\com\lamp\Demo.class
            *
            * */
            String replace = file.getAbsolutePath()
                    .replace("\\", ".")
                    .replace("F:.OpenSource.jvm.out.production.jvm.","")
                    .replace(".class","");
         //   System.out.println(replace);
          Class<?> aClass = loader.loadClass(replace);
           listclasses.add(aClass);

        }else {
            File[] files = file.listFiles();
            for (File file1 : files) {
                loaderFile(file1.getPath(),listclasses,loader);
            }

        }



    }
}
