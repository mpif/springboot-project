package com.codefans.springboot.task;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author: codefans
 * @Date: 2022-07-29 14:46
 */

public class FindClassTask extends RecursiveTask<List<String>> {

    private List<String> jarList;

    private int startIndex;

    private int endIndex;

    private String targetClsName;

    private int temp = 100;

    public FindClassTask(List<String> jarList, String targetClsName, int startIndex, int endIndex) {
        this.jarList = jarList;
        this.targetClsName = targetClsName;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected List<String> compute() {

        // 1.当拆解的任务大于临界点值，则继续拆解任务
        if((endIndex - startIndex) > temp){
            // a.将大任务拆解为2个小任务
            int middle = (endIndex + startIndex) / 2;

            // b.任务1  比如：累加1-100的和 ，任务1累加：1-50的和
            FindClassTask task1 = new FindClassTask(jarList, targetClsName, startIndex, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列

            // c.任务2  比如：累加1-100的和 ，任务2累加：51-100的和
            FindClassTask task2 = new FindClassTask(jarList, targetClsName, middle+1, endIndex);
            task2.fork();

            // d.返回子任务的结果和
            task1.join();
            task2.join();

            // 2.当拆解的任务小于临界点值，直接计算并返回最后结果
        }else{
            for(int i = startIndex; i <= endIndex; i ++) {
                String jarPath = jarList.get(i);
                try {
                    JarFile jarFile = new JarFile(jarPath);
                    Enumeration<JarEntry> entrys = jarFile.entries();
                    while (entrys.hasMoreElements()) {
                        JarEntry jarEntry = entrys.nextElement();
                        if(jarEntry.getName().endsWith(targetClsName)) {
                            System.out.println("jar [" + jarPath + "] contains class[" + targetClsName + "]");
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        return null;
    }


}
