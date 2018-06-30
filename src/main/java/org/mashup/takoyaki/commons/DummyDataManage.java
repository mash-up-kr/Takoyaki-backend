package org.mashup.takoyaki.commons;

import org.mashup.takoyaki.api.map.dto.TakoyakiData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

@Component
public class DummyDataManage {

    private ResourceLoader dummyDataReader;
    private final Map<String, TakoyakiData> dummyData = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(DummyDataManage.class);

    @Autowired
    public DummyDataManage(ResourceLoader dummyDataReader) {
        this.dummyDataReader = dummyDataReader;
    }

    @PostConstruct
    public void initDummyData() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(dummyDataReader.getResource("classpath:dummyData.txt").getInputStream(), "UTF-8"));
            bufferedReader.lines().forEach(elem -> {
                List<String> list = Arrays.asList(elem.split(","));
                TakoyakiData data = new TakoyakiData();
                logger.info("list -> truckName : {}", list.get(0));
                logger.info("list -> lat : {}", list.get(1));
                logger.info("list -> long : {}", list.get(2));
                logger.info("list -> region : {}", list.get(3));
                logger.info("list -> description : {}", list.get(4));
                logger.info("list -> type : {}", list.get(5));

                data.setTruckName(list.get(0));
                data.setLatitude(Double.parseDouble(list.get(1)));
                data.setLongitude(Double.parseDouble(list.get(2)));
                data.setRegion(list.get(3));
                data.setDescription(list.get(4));
                data.setType(list.get(5));

                dummyData.put(list.get(0), data);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Optional.ofNullable(bufferedReader).ifPresent(reader -> {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public Map<String, TakoyakiData> getDummyData() {
        return this.dummyData;
    }

}
