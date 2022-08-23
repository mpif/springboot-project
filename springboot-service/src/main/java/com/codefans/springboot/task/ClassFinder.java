package com.codefans.springboot.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Author: codefans
 * @Date: 2022-07-29 14:23
 */

public class ClassFinder {

    public static void main(String[] args) {
        ClassFinder cf = new ClassFinder();
        cf.find();
    }

    public void find() {

        String className = "AuthenticationManagerResolver.class";
        String rootPath = "D:\\maven-repository";
        this.findClass(className, rootPath);

    }

    private void findClass(String className, String rootPath) {
        List<String> jarList = new ArrayList<>(1024);
        this.collectJars(rootPath, jarList);
        System.out.println("jar文件数=" + jarList.size());
        this.findClassInJars(className, jarList);
    }

    private void collectJars(String rootPath, List<String> jarList) {
        File dir = new File(rootPath);
        if(dir.isDirectory()) {
            File[] fileArr = dir.listFiles();
            for(File f : fileArr) {
                if(f.isDirectory()) {
                    this.collectJars(rootPath + File.separator + f.getName(), jarList);
                } else {
                    if(f.getName().endsWith(".jar")) {
                        jarList.add(f.getAbsolutePath());
                    }
                }
            }
        } else {
            if(dir.getName().endsWith(".jar")) {
                jarList.add(dir.getAbsolutePath());
            }
        }
    }

    private void findClassInJars(String className, List<String> jarList) {
        long start = System.currentTimeMillis();

        // 1. 初始化一个线程池
        ForkJoinPool pool = new ForkJoinPool();

        // 2. 将任务提交到池中 （开始值：1 结束值：10_0000_0000）
        ForkJoinTask<List<String>> result = pool.submit(new FindClassTask(jarList, className, 0, jarList.size() - 1));

        // 3. 获取结果（可能会阻塞）
        List<String> clsList = null;
        try {
            clsList = result.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("执行毫秒："+(end-start));

    }

}
