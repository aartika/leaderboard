import java.util.*;
import java.util.SortedMap;


/**
 * Created by aartika.rai on 12/26/15.
 */
public class SkipListNavigableMap<K extends Comparable<? super K>, V>  extends HashMap<K, V> implements NavigableMap<K, V> {

    private SkipList<K> skipList;

    public SkipListNavigableMap() {
        this.skipList = new SkipList<K>();
    }

    public SkipList<K> getSkipList() {
        return skipList;
    }

    @Override
    public V put(K key, V value) {
        skipList.delete(key);
        skipList.insert(key);
        return super.put(key, value);
    }

    public Map.Entry<K, V> lowerEntry(K key) {
        SkipList.Node<K> prev = skipList.search(key).getPrev();
        return prev == null ? null : new MapEntry<K, V>(prev.getEle(), get(prev.getEle()));
    }

    public K lowerKey(K key) {
        SkipList.Node<K> prev = skipList.search(key).getPrev();
        return prev == null ? null : prev.getEle();
    }

    public Entry<K, V> higherEntry(K key) {
        SkipList.Node<K> next = skipList.search(key).getNext();
        return next == null ? null : new MapEntry<K, V>(next.getEle(), get(next.getEle()));
    }

    public K higherKey(K key) {
        SkipList.Node<K> next = skipList.search(key).getNext();
        return next == null ? null : next.getEle();
    }

    public Entry<K, V> firstEntry() {
        SkipList.Node<K> first = skipList.firstEntry();
        return new MapEntry<K, V>(first.getEle(), get(first.getEle()));
    }

    public Entry<K, V> lastEntry() {
        SkipList.Node<K> last = skipList.lastEntry();
        return new MapEntry<K, V>(last.getEle(), get(last.getEle()));
    }

    @Override
    public V remove(Object key) {
        skipList.delete( (K) key );
        return super.remove(key);
    }

    public K firstKey() {
        return skipList.firstEntry().getEle();
    }

    public K lastKey() {
        return skipList.lastEntry().getEle();
    }

    public Entry<K, V> floorEntry(K key) {
        throw new UnsupportedOperationException();
    }

    public K floorKey(K key) {
        throw new UnsupportedOperationException();
    }

    public Entry<K, V> ceilingEntry(K key) {
        throw new UnsupportedOperationException();
    }

    public K ceilingKey(K key) {
        throw new UnsupportedOperationException();
    }

    public Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    public Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public NavigableMap<K, V> descendingMap() {
        throw new UnsupportedOperationException();
    }

    public NavigableSet<K> navigableKeySet() {
        throw new UnsupportedOperationException();
    }

    public NavigableSet<K> descendingKeySet() {
        throw new UnsupportedOperationException();
    }

    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        throw new UnsupportedOperationException();
    }

    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        throw new UnsupportedOperationException();
    }

    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        throw new UnsupportedOperationException();
    }

    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        throw new UnsupportedOperationException();
    }

    public SortedMap<K, V> headMap(K toKey) {
        throw new UnsupportedOperationException();
    }

    public SortedMap<K, V> tailMap(K fromKey) {
        throw new UnsupportedOperationException();
    }

    public Comparator<? super K> comparator() {
        return new Comparator<K>() {
            public int compare(K o1, K o2) {
                return o1.compareTo(o2);
            }
        };
    }

    public class MapEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
}