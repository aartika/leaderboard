import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aartika.rai on 12/26/15.
 */
public class SkipListTest {


    @Test
    public void test_with_30_50() {
        SkipList<Integer> list = new SkipList<Integer>();
        list.insert(30);
        list.insert(50);
        assertEquals(2, list.getSize());
        assertEquals(new Integer(30), list.firstEntry().getEle());
        assertEquals(new Integer(50), list.lastEntry().getEle());
        assertNotNull(list.search(30));
        assertNotNull(list.search(50));
        assertNull(list.search(51));
        assertNull(list.search(33));
    }

    @Test
    public void test_with_30_40_50_60_70_80_90() {
        SkipList<Integer> list = new SkipList<Integer>();
        list.insert(40);
        list.insert(50);
        list.insert(30);
        list.insert(70);
        list.insert(80);
        list.insert(60);
        list.insert(90);
        list.insert(34);
        list.insert(12);
        System.out.println(list.level);
        list.print();
        assertEquals(9, list.getSize());
        assertEquals(new Integer(12), list.firstEntry().getEle());
        assertEquals(new Integer(90), list.lastEntry().getEle());
        assertNotNull(list.search(30) != null);
        assertNotNull(list.search(40));
        assertNotNull(list.search(50));
        assertNotNull(list.search(60));
        assertNotNull(list.search(70));
        assertNotNull(list.search(80));
        assertNotNull(list.search(90));
        assertNull(list.search(33));
        assertNull(list.search(51));
        assertNull(list.search(63));
        assertNull(list.search(133));
    }

    @Test
    public void test_with_50_30() {
        SkipList<Integer> list = new SkipList<Integer>();
        list.insert(50);
        list.insert(30);
        list.print();
        assertEquals(2, list.getSize());
        assertEquals(new Integer(30), list.firstEntry().getEle());
        assertEquals(new Integer(50), list.lastEntry().getEle());
        assertNotNull(list.search(30));
        System.out.println(list.level);
        list.delete(30);
        System.out.println(list.level);
        list.print();
        assertNull(list.search(30));
        list.delete(50);
        list.print();
        assertNull(list.search(50));
    }

    @Test
    public void test_delete_with_30_40_50_60_70_80_90() {
        SkipList<Integer> list = new SkipList<Integer>();
        list.delete(10);
        list.insert(40);
        list.insert(50);
        list.insert(30);
        list.insert(70);
        list.insert(80);
        list.insert(60);
        list.insert(90);
        list.insert(34);
        list.insert(12);
        System.out.println(list.level);
        list.print();
        list.delete(33);
        assertEquals(9, list.getSize());
        list.delete(40);
        assertNull(list.search(40));
        list.delete(12);
        assertNull(list.search(12));
        list.delete(90);
        assertNull(list.search(90));
        list.delete(30);
        assertNull(list.search(30));
        list.delete(30);
        assertNull(list.search(30));
        list.delete(50);
        assertNull(list.search(50));
    }

}