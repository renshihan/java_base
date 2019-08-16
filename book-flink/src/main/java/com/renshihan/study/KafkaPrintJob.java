package com.renshihan.study;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaPrintJob {
    private static final Logger logger= LoggerFactory.getLogger(KafkaPrintJob.class);
    public static void main(String[] args) {
        final StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props=new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  //key 反序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest"); //value 反序列化
        DataStreamSource<String> dataStreamSource=env.addSource(
                new FlinkKafkaConsumer011<String>("testDemo",new SimpleStringSchema(),props)
        ).setParallelism(1);
        dataStreamSource.print();
        try {
            env.execute("kafkaPrintJob----execute");
        } catch (Exception e) {
            logger.error("flink启动异常",e);
        }
    }
}
