package com.joaoplima99.mocking.run;

import com.joaoplima99.mocking.model.MockingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MockingBean.Utils.getMockingBeanList().stream().collect(Collectors.groupingBy(MockingBean::hashCode))
                .forEach((hash, mockingBeans) -> {
                    LOG.info(hash.toString());
                    mockingBeans.forEach(bean -> LOG.info(bean.toString()));
                });
    }
}