package org.example;

public class Pair<K, V> {

    private K k;
    private V v;

    public K getK() {
        return k;
    }

    public void setK(K k) {
        if (k == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (k instanceof String && ((String) k).isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty");
        }
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        if (v == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        if (v instanceof String && ((String) v).isEmpty()) {
            throw new IllegalArgumentException("Value cannot be empty");
        }
        this.v = v;
    }

    public String getKV() {
        return new Pair<>(k, v).toString();
    }


    public void setKV(K k, V v) {
        setK(k);
        setV(v);
    }

    public Pair(K k, V v) {
        setK(k);
        setV(v);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
