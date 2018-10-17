package com.tree;

/**
 * @author wangyongchao
 * @date 2018/10/15  15:51
 */
public class Node<T, V> extends Entry<T,V> {

    public Node(T key, V value) {
        super(key, value);
    }

    public Node(T key, V value, Integer height) {
        super(key, value);
        this.height = height;
    }

    public Node() {
        super(null, null);
    }

    private Integer height;

    public Node<T, V> left;

    public Node<T, V> right;

    public Node<T, V> parent;

    public Boolean hasLeft() {
        return left!=null;
    }

    public Boolean hasRight() {
        return right!=null;
    }

    public Integer getHeight() {
        if (height == null) {
            return 0;
        }
        return height;
    }

    public Node setHeight(Integer height) {
        this.height = height;
        return this;
    }
}

 class Entry<K, V> {

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K key;

    public V value;

}