package com.joaoplima99.utils;

import com.joaoplima99.mocking.model.MockingBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.joaoplima99.utils.StreamUtils.mergeLists;
import static com.joaoplima99.utils.StreamUtils.requireNonNullElements;
import static com.google.common.collect.Lists.newArrayList;
import static com.joaoplima99.mocking.model.MockingBean.Utils.getMockingBeanList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(MockitoJUnitRunner.class)
public class StreamUtilsTest {

    private static void assertThrowsNPE_requireNonNullElements(List<?> list) {
        assertThrows(NullPointerException.class, () -> requireNonNullElements(list));
    }

    @Test
    public void assertNPE_when_null_list_on_requireNonNullElements() {
        assertThrowsNPE_requireNonNullElements(null);
    }

    @Test
    public void assertNPE_when_list_populated_with_nulls_on_requireNonNullElements() {
        assertThrowsNPE_requireNonNullElements(newArrayList(null, null, null, null));
    }

    @Test
    public void assertNPE_when_at_least_one_element_is_null_on_requireNonNullElements() {
        assertThrowsNPE_requireNonNullElements(newArrayList("Test1", "Test2", null, "Test3"));
    }

    @Test
    public void assert_PASS_for_compliant_list_on_require_NonNullElements() {
        assertDoesNotThrow(() -> requireNonNullElements(MockingBean.Utils.getMockingBeanList()));
    }

    private static void assertThrowsNPE_mergeLists(List... list) {
        assertThrows(NullPointerException.class, () -> mergeLists(list));
    }

    @Test
    public void assertNPE_when_null_array_of_lists_mergeLists() {
        assertThrowsNPE_mergeLists(null);
    }

    @Test
    public void assertNPE_when_at_least_one_list_is_null_on_mergeLists() {
        assertThrowsNPE_mergeLists(newArrayList("String", "Test", "JUnit"), null, newArrayList("Java"));
    }

    @Test
    public void assertNPE_when_at_least_one_list_has_null_elements_on_mergeLists() {
        assertThrowsNPE_mergeLists(newArrayList("String", "Test"), newArrayList(null, "Book"), newArrayList());
    }

    @Test
    public void assert_PASS_for_compliant_lists_on_mergeLists() {
        List<MockingBean> resultList = mergeLists(getMockingBeanList(1), getMockingBeanList(2));
        List<MockingBean> expectedList = getMockingBeanList(3);
        assertEquals(resultList.size(), expectedList.size());
        for(int i = 0; i < resultList.size(); i++) assertEquals(resultList.get(i), expectedList.get(i));
    }
}