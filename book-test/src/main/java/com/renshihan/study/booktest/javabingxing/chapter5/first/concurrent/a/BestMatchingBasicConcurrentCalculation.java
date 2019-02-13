/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 18:45
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter5.first.concurrent.a;

import com.renshihan.study.booktest.javabingxing.chapter5.first.common.BestMatchingData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.Executors.*;

/**
 * 该类负责为处理整个词典创建必须的任务，执行这些任务的执行器，并且在执行器中
 * 控制这些任务的执行。
 */
public class BestMatchingBasicConcurrentCalculation {
    //
    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) throws InterruptedException, ExecutionException {
        int numCores=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor=(ThreadPoolExecutor) newFixedThreadPool(numCores);
        int size=dictionary.size();
        int step=size/numCores;
        int startIndex,endIndex;
        List<Future<BestMatchingData>> results=new ArrayList<>();
        //使用sumbit()方法将其发送给执行器，sumbit方法会立即返回，不会等待任务执行
        for (int i = 0; i < numCores; i++) {
            startIndex=i * step;
            if(i==numCores-1){
                endIndex=dictionary.size();
            }else {
                endIndex=(i+1)*step;
            }
            BestMatchingBasicTask task=new BestMatchingBasicTask(startIndex,endIndex,dictionary,word);
            Future<BestMatchingData> future=executor.submit(task);
            results.add(future);
        }

        //可以调用执行器的shutdown()方法来结束其执行。并且对Future对象列表执行迭代操作以获得
        //每个任务的执行结果。
        executor.shutdown();
        List<String> words=new ArrayList<>();
        int minDistance=Integer.MAX_VALUE;
        for (Future<BestMatchingData> result : results) {
            BestMatchingData data=result.get();
            if(data.getDistance()<minDistance){
                words.clear();
                words.addAll(data.getWords());
            }else if(data.getDistance()==minDistance){
                words.addAll(data.getWords());
            }
        }
        return new BestMatchingData(words,minDistance);

    }
}