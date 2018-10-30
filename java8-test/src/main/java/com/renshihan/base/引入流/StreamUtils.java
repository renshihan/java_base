package com.renshihan.base.引入流;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/28 13:54
 */
@Slf4j
public class StreamUtils {
    /**
     * 获取流 集合
     * */
    public static Stream getStream(List<String> list){
        return list.stream();
    }

    /**
     * 获取流 数组
     * */
    public static Stream getStream(String[] names){
        return Arrays.stream(names);
    }


    /**
     * 获取流 文件
     * */
    public static Stream getStream(String filePath){
        try {
            return Files.lines(Paths.get(filePath), Charset.defaultCharset());
        }catch (Exception e){
            log.error("文件获取流异常",e);
            return null;
        }
    }
}
