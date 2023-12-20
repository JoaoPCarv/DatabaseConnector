package com.joaoplima99.mocking.model;

import com.google.common.base.MoreObjects;
import com.joaoplima99.mocking.persistence.converter.converter.RegionConverter;
import com.joaoplima99.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.joaoplima99.utils.StreamUtils.requireNonNullElements;
import static com.joaoplima99.utils.StringUtils.getAppendedCharString;

@Entity
@Table(name = "MockingBean")
public class MockingBean {

    public static Logger LOG = LoggerFactory.getLogger(MockingBean.class);

    @Column(name = "name_mockbean")
    private String name;
    @Convert(converter = RegionConverter.class)
    @Column(name = "name_region")
    private Region region;
    @Id
    @GeneratedValue()
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
        return ObjectUtils.getHashCode(this.getName(), this.region.getName(), Integer.toString(this.getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof MockingBean)) return false;
        if (this == o) return true;
        //Parameter o is certainly an instance of MockingBean
        MockingBean that = (MockingBean) o;
        return this.hashCode() == that.hashCode()
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getRegion(), that.getRegion())
                && this.getId() == that.getId();
    }

    public static MockingBean newMockingBean() {
        return new MockingBean();
    }

    public static final class Utils {

        //This internal class should not be instantiated.
        @Deprecated(since = "1.0")
        private Utils() {}

        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1) {
            return newArrayList(newMockingBean().withName(name1).withRegion(region1).withId(id1));
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2) {
            return newArrayList(
                    newMockingBean().withName(name1).withRegion(region1).withId(id1),
                    newMockingBean().withName(name2).withRegion(region2).withId(id2)
            );
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2,
                                                            String name3, Region region3, int id3) {
            return newArrayList(
                    newMockingBean().withName(name1).withRegion(region1).withId(id1),
                    newMockingBean().withName(name2).withRegion(region2).withId(id2),
                    newMockingBean().withName(name3).withRegion(region3).withId(id3)
            );
        }
        public static List<MockingBean> getMockingBeanBatch(String name1, Region region1, int id1,
                                                            String name2, Region region2, int id2,
                                                            String name3, Region region3, int id3,
                                                            String name4, Region region4, int id4) {
            return newArrayList(
                    newMockingBean().withName(name1).withRegion(region1).withId(id1),
                    newMockingBean().withName(name2).withRegion(region2).withId(id2),
                    newMockingBean().withName(name3).withRegion(region3).withId(id3),
                    newMockingBean().withName(name4).withRegion(region4).withId(id4)
            );
        }

        public static List<MockingBean> getMockingBeanBatch(String[] names, Region[] regions, int[] ids) {
            if(!(names.length == regions.length && names.length == ids.length)) {
                throw new UnsupportedOperationException("A bean batch could now be constructed " +
                        "because the lengths of the arrays of parameters don't match.");
            }
            List<MockingBean> mockingBeans = new ArrayList<>(names.length);
            for(int i = 0; i < names.length; i++) mockingBeans.add(newMockingBean().withName(names[i]).withRegion(regions[i]).withId(ids[i]));
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
                            newMockingBean().withName("Joao").withRegion(Region.BRAZIL).withId(00),
                            newMockingBean().withName("Pedro").withRegion(Region.USA).withId(01),
                            newMockingBean().withName("Lima").withRegion(Region.CHINA).withId(10),
                            newMockingBean().withName("AC").withRegion(Region.CHINA).withId(11),
                            newMockingBean().withName("Clean").withRegion(Region.BRAZIL).withId(00),
                            newMockingBean().withName("Cloud").withRegion(Region.BRAZIL).withId(01),
                            newMockingBean().withName("SEK").withRegion(Region.CHINA).withId(10),
                            newMockingBean().withName("io").withRegion(Region.USA).withId(11)
                    );
                default: return null;
            }
        }

        public static MockingBean getRandomMockingBean() {
            List<MockingBean> list = getMockingBeanList();
            int drawn = ThreadLocalRandom.current().nextInt(0, list.size() - 1);
            return list.get(drawn);
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