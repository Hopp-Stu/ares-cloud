package com.ares.system.service;

import com.ares.system.client.RedisClient;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.Constants;

import com.ares.system.dao.IIndexDao;
import com.ares.system.model.PanelGroup;
import com.ares.system.model.line.Legend;
import com.ares.system.model.line.LineChart;
import com.ares.system.model.line.Series;
import com.ares.system.model.line.XAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/14
 * @see: com.ares.system.service IndexService.java
 **/
@Slf4j
@Service
public class IndexService {

    @Resource
    private IIndexDao indexDao;
    @Resource
    private RedisClient redisClient;

    public List<PanelGroup> getPanelGroup() {
        List<PanelGroup> panelGroups = new ArrayList<>();
        PanelGroup panelGroup = new PanelGroup();
        panelGroup.setName("在线人数");
        panelGroup.setType("newVisitis");
        panelGroup.setIcon("peoples");
        panelGroup.setStartVal(0);
        panelGroup.setEndVal(getOnlinePeople());
        panelGroup.setDuration(2000);
        panelGroups.add(panelGroup);
        panelGroup = new PanelGroup();
        panelGroup.setName("消息");
        panelGroup.setType("messages");
        panelGroup.setIcon("message");
        panelGroup.setStartVal(0);
        panelGroup.setEndVal(81212);
        panelGroup.setDuration(3000);
        panelGroups.add(panelGroup);
        panelGroup = new PanelGroup();
        panelGroup.setName("定时任务");
        panelGroup.setType("jobs");
        panelGroup.setIcon("job");
        panelGroup.setStartVal(0);
        panelGroup.setEndVal(indexDao.getJobNumber());
        panelGroup.setDuration(2000);
        panelGroups.add(panelGroup);
        panelGroup = new PanelGroup();
        panelGroup.setName("日志");
        panelGroup.setType("logs");
        panelGroup.setIcon("log");
        panelGroup.setStartVal(0);
        panelGroup.setEndVal(indexDao.getLogNumber());
        panelGroup.setDuration(2000);
        panelGroups.add(panelGroup);
        return panelGroups;
    }


    public Map<String, Object> getLineChartData() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("expectedData", Arrays.asList(100, 120, 161, 134, 105, 160, 165));
        data.put("actualData", Arrays.asList(120, 82, 91, 154, 162, 140, 145));
        map.put("newVisitis", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(200, 192, 120, 144, 160, 130, 140));
        data.put("actualData", Arrays.asList(180, 160, 151, 106, 145, 150, 130));
        map.put("messages", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(80, 100, 121, 104, 105, 90, 100));
        data.put("actualData", Arrays.asList(120, 90, 100, 138, 142, 130, 130));
        map.put("jobs", data);

        data = new HashMap<>();
        data.put("expectedData", Arrays.asList(130, 140, 141, 142, 145, 150, 160));
        data.put("actualData", Arrays.asList(120, 82, 91, 154, 162, 140, 130));
        map.put("shoppings", data);

        return map;
    }

    public LineChart getLineChart() {
        LineChart lineChart = new LineChart();
        XAxis xAxis = new XAxis();
        Legend legend = new Legend();
        List<Series> series = new ArrayList<>();

        xAxis.setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"});
        xAxis.setBoundaryGap(false);
        xAxis.setAxisTick(new HashMap<String, Object>() {{put("show", true);}});
        lineChart.setXAxis(xAxis);
        legend.setData(new String[]{"expected", "actual"});
        lineChart.setLegend(legend);

        Series ser = new Series();
        ser.setName("expected");
        ser.setItemStyle(new HashMap<String,Object>() {{
            put("normal", new HashMap<String,Object>(){{
                put("color", "#FF005A");
                put("lineStyle",new HashMap<String,Object>(){{
                    put("color", "#FF005A");
                    put("width", "2");
                }});
            }});
        }});
        ser.setSmooth(true);
        ser.setType("line");
        ser.setData(new Number[]{});
        ser.setAnimationDuration(2800);
        ser.setAnimationEasing("cubicInOut");
        series.add(ser);
        lineChart.setSeries(series);
        return lineChart;
    }

    private int getOnlinePeople() {
        String pattern = Constants.LOGIN_INFO + "*";
        BaseResult result = redisClient.getKeysByPattern(pattern);
        List<String> keys = (List<String>) result.get("data");
        return keys.size();
    }
}
