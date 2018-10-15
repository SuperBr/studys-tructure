package com.tree;

/**
 * @author wangyongchao
 * @date 2018/10/15  15:51
 */
public class Node<T, V> extends Entry<T,V> {

    public Node(T key, V value) {
        super(key, value);
    }

    public Node() {
        super(null, null);
    }

    public Node left;

    public Node right;

    public Node parent;

    public Boolean hasLeft() {
        return left!=null;
    }

    public Boolean hasRight() {
        return right!=null;
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