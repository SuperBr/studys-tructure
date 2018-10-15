package com.tree.searchTree;

import com.tree.AbsTree;
import com.tree.Node;

import java.util.Comparator;

/**
 * @author wangyongchao
 * @date 2018/10/15  15:58
 */
public class SearcherTree implements AbsTree<Integer, String> {

    private Node<Integer, String> root;

    public SearcherTree(Integer k, String v) {
        this.root = new Node(k, v);
    }

    public String insert(Integer k, String v) {
        if (root == null) {
            this.root = new Node(k, v);
            return v;
        }
        Node node = new Node(k, v);
        return insert(root, node);

    }

    private String insert(Node<Integer, String> root, Node<Integer, String> curNode) {

        if (root.key - curNode.key == 0) {
            this.root = curNode;
            return curNode.value;
        } else if (root.key > curNode.key) {
            if (root.hasLeft()) {
                return insert(root.left, curNode);
            } else {
                root.left = curNode;
            }

        } else {
            if (root.hasRight()) {
                return insert(root.right, curNode);
            } else {
                root.right = curNode;
            }
        }
        return curNode.value;
    }

    public String find(Integer integer) {
        if (root == null) {
            return null;
        }
        return find(root,integer).value;
    }

    private Node<Integer,String> find(Node<Integer, String> root, Integer key) {
        if (root.key -key == 0) {
            return root;
        } else if (root.key > key) {
            if (root.hasLeft()) {
                return find(root.left, key);
            }
        } else {
            if (root.hasRight()) {
                return find(root.right, key);
            }
        }
        return new Node();
    }


    public Boolean delete(Integer integer) {
        return null;
    }
}
