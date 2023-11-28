package com.joaoplima99.mocking.model;

import com.google.common.base.MoreObjects;
import com.google.common.hash.HashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.joaoplima99.utils.StringUtils.getAppendedCharString;
import static com.joaoplima99.utils.StreamUtils.requireNonNullElements;
import static com.google.common.collect.Lists.newArrayList;

public class MockingBean {

    public static Logger LOG = LoggerFactory.getLogger(MockingBean.class);

    private String name;
    private Region region;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MockingBean() {
    }

    public MockingBean(String name, Region region, int id) {
        setName(name);
        setRegion(region);
        setId(id);
    }

    public MockingBean withName(String name) {
        setName(name);
        return this;
    }

    public MockingBean withRegion(Region region) {
        setRegion(region);
        return this;
    }

    public MockingBean withId(int id) {
        setId(id);
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", getName())
                .add("Region", getRegion())
                .add("Id", getId())
                .toString();
    }

    @Override
    public int hashCode() {
        return HashCode.fromBytes(this.getName().getBytes()).hashCode()
                + HashCode.fromBytes(this.getRegion().getName().getBytes()).hashCode()
                + HashCode.fromBytes(Integer.toString(this.getId()).getBytes()).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof MockingBean)) return false;
        MockingBean that = (MockingBean) o;
        return this.hashCode() == that.hashCode()
                && this.getName().equals(that.getName())
                && this.getRegion().equals(that.getRegion())
                && this.getId() == that.getId();
    }

    public static MockingBean newBean() {
        return new MockingBean();
    }

    public final class Utils {

        //This internal class should not be instantiated.
        @Deprecated(since = "1.0")
        private Utils() {}

        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1) {
            return newArrayList(newBean().withName(name1).withRegion(region1).withId(id1));
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2) {
            return newArrayList(
                    newBean().withName(name1).withRegion(region1).withId(id1),
                    newBean().withName(name2).withRegion(region2).withId(id2)
            );
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2,
                                                            String name3, Region region3, int id3) {
            return newArrayList(
                    newBean().withName(name1).withRegion(region1).withId(id1),
                    newBean().withName(name2).withRegion(region2).withId(id2),
                    newBean().withName(name3).withRegion(region3).withId(id3)
            );
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2,
                                                            String name3, Region region3, int id3,
                                                            String name4, Region region4, int id4) {
            return newArrayList(
                    newBean().withName(name1).withRegion(region1).withId(id1),
                    newBean().withName(name2).withRegion(region2).withId(id2),
                    newBean().withName(name3).withRegion(region3).withId(id3),
                    newBean().withName(name4).withRegion(region4).withId(id4)
            );
        }

        public static List<MockingBean> getMockingBeanBatch(String[] names, Region[] regions, int[] ids) {
            if(!(names.length == regions.length && names.length == ids.length)) {
                throw new UnsupportedOperationException("A bean batch could now be constructed " +
                        "because the lengths of the arrays of parameters don't match.");
            }
            List<MockingBean> mockingBeans = new ArrayList<>(names.length);
            for(int i = 0; i < names.length; i++) mockingBeans.add(newBean().withName(names[i]).withRegion(regions[i]).withId(ids[i]));
            return mockingBeans;
        }

        public static List<MockingBean> getMockingBeanList() {
            String[] names = new String[] {
                    "Joao",
                    "SEK",
                    "Cell",
                    "Alligator",
                    "Casmurro",
                    "Tech",
                    "Poli",
                    "FOB",
                    "CapsLock",
                    "Manual"};
            Region[] regions = new Region[] {
                    Region.BRAZIL,
                    Region.BRAZIL,
                    Region.CHINA,
                    Region.USA,
                    Region.CHINA,
                    Region.BRAZIL,
                    Region.USA,
                    Region.CHINA,
                    Region.USA,
                    Region.USA
            };
            int[] ids = new int[] {1,2,3,4,5,6,7,8,9,10};
            return getMockingBeanBatch(names, regions, ids);
        }

        public static List<MockingBean> getMockingBeanList(int type) {
            switch(type) {
                case 1 : return getMockingBeanBatch(
                        "Joao", Region.BRAZIL, 00,
                        "Pedro", Region.USA, 01,
                        "Lima", Region.CHINA, 10,
                        "AC", Region.CHINA, 11);
                case 2 :
                    return getMockingBeanBatch(
                            "Clean", Region.BRAZIL, 00,
                            "Cloud", Region.BRAZIL, 01,
                            "SEK", Region.CHINA, 10,
                            "io", Region.USA, 11);
                case 3 :
                    return newArrayList(
                            newBean().withName("Joao").withRegion(Region.BRAZIL).withId(00),
                            newBean().withName("Pedro").withRegion(Region.USA).withId(01),
                            newBean().withName("Lima").withRegion(Region.CHINA).withId(10),
                            newBean().withName("AC").withRegion(Region.CHINA).withId(11),
                            newBean().withName("Clean").withRegion(Region.BRAZIL).withId(00),
                            newBean().withName("Cloud").withRegion(Region.BRAZIL).withId(01),
                            newBean().withName("SEK").withRegion(Region.CHINA).withId(10),
                            newBean().withName("io").withRegion(Region.USA).withId(11)
                    );
                default: return null;
            }
        }

        public static void printRegionalBeans(List<MockingBean> mockingBeans) {
            requireNonNullElements(mockingBeans);
            mockingBeans.stream().collect(Collectors.groupingBy(mockingBean -> mockingBean.getRegion())).forEach(
                    (region, rBean) -> {
                        String message = "REGION [" + region.getStatusOfRegion() + "]"
                                + " - Name: " + region.getName() + " | Number of clusters: " + region.getNumOfClusters()
                                + " ; Number of elements per region: " + rBean.size() + ".";
                        System.out.println(message);
                        System.out.println(getAppendedCharString('-', message.length()));
                        rBean.forEach(System.out::println);
                        System.out.println();
                    }
            );
        }
    }
}