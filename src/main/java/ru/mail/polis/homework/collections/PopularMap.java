package ru.mail.polis.homework.collections;


import java.util.*;
import java.util.stream.Collectors;


/**
 * Написать структуру данных, реализующую интерфейс мапы + набор дополнительных методов.
 * 4 дополнительных метода должны возвращать самый популярный ключ и его популярность. (аналогично для самого популярного значения)
 * Популярность - это количество раз, который этот ключ/значение учавствовал/ло в других методах мапы, такие как
 * containsKey, get, put, remove (в качестве параметра и возвращаемого значения).
 * Считаем, что null я вам не передаю ни в качестве ключа, ни в качестве значения
 *
 * Так же надо сделать итератор (подробности ниже).
 *
 * Важный момент, вам не надо реализовывать мапу, вы должны использовать композицию.
 * Вы можете использовать любые коллекции, которые есть в java.
 *
 * Помните, что по мапе тоже можно итерироваться
 *
 *         for (Map.Entry<K, V> entry : map.entrySet()) {
 *             entry.getKey();
 *             entry.getValue();
 *         }
 *
 * Всего 10 тугриков (3 тугрика за общие методы, 2 тугрика за итератор, 5 тугриков за логику популярности)
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class PopularMap<K, V> implements Map<K, V> {

    private final Map<K, V> map;
    private final Map<K,Integer> keyCounter;
    private final Map<V,Integer> valueCounter; //мапа для подсчета кол-ва вызовов каждого значения

    private V mostPopularValue; // самое популярное значение на данный момент
    private K mostPopularKey;


    public PopularMap() {
        this.map = new HashMap<>();
        this.keyCounter = new HashMap<>();
        this.valueCounter = new HashMap<>();
    }

    public PopularMap(Map<K, V> map) {
        this.map = map;
        this.keyCounter = new HashMap<>();
        this.valueCounter = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        incrementKey((K) key);
        return map.containsKey(key);
    }

    private void incrementKey(K key) {
        int popular = keyCounter.merge(key, 1, Integer::sum);
        if (keyCounter.getOrDefault(mostPopularKey, 0) < popular) {
            mostPopularKey = key;
        }
    }
    

    @Override
    public boolean containsValue(Object value) {
        incrementValue((V) value);
        return map.containsValue(value);
    }

    private void incrementValue(V value) { // автоматически всегда поддерживаем актуальное состояние - добавление/удаление значения выполняется О(1)
        int popular = valueCounter.merge(value, 1, Integer::sum);
        if (valueCounter.getOrDefault(mostPopularValue, 0) < popular) {
            mostPopularValue = value;
        }
    }

    @Override
    public V get(Object key) {
        V value = map.get(key);
        incrementValue((V) value);
        incrementKey((K) key);
        return value;
    }

    /**Если ключ уже существует, и его старое значение было заменено новым значением,
     *метод возвращает старое значение, которое было связано с этим ключом.
     */

    @Override
    public V put(K key, V value) { //
        incrementValue((V) value);
        V oldValue = map.get(key);
        incrementValue(oldValue);
        incrementKey(key);
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        V oldValue = map.get(key);
        incrementValue((V) oldValue);
        incrementKey((K)key);
        map.remove(key);
        return oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
//        throw new UnsupportedOperationException("putAll");
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }



    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    /**
     * Возвращает самый популярный, на данный момент, ключ
     */
    public K getPopularKey() {
        return mostPopularKey;
    }


    /**
     * Возвращает количество использование ключа
     */
    public int getKeyPopularity(K key) {
        return keyCounter.getOrDefault(key, 0);
    }

    /**
     * Возвращает самое популярное, на данный момент, значение. Надо учесть что значени может быть более одного
     */
    public V getPopularValue() {
        return mostPopularValue;
    }

    /**
     * Возвращает количество использований значений в методах: containsValue, get, put (учитывается 2 раза, если
     * старое значение и новое - одно и тоже), remove (считаем по старому значению).
     */
    public int getValuePopularity(V value) {
        return valueCounter.getOrDefault(value, 0);
    }

    /**
     * Вернуть итератор, который итерируется по значениям (от самых НЕ популярных, к самым популярным)
     * 2 тугрика
     */
    public Iterator<V> popularIterator() {
        return valueCounter.entrySet().stream()
                .sorted(Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .iterator();

    }
}
