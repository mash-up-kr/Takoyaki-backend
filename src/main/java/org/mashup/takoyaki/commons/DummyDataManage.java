package org.mashup.takoyaki.commons;

import org.mashup.takoyaki.api.map.dto.TakoyakiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class DummyDataManage {

    private ResourceLoader dummyDataReader;
    private final Map<String, TakoyakiData> dummyData = new HashMap<>();

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
