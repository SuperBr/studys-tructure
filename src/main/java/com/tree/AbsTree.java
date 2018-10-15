package com.tree;

/**
 * @author wangyongchao
 * @date 2018/10/15  15:46
 */
public interface AbsTree<K, V> {


    V insert(K k, V v);

    V find(K k);

    Boolean delete(K k);


}
