package com.tree.balance2Tree;

import com.tree.AbsTree;
import com.tree.Node;

/**
 * @author wangyongchao
 * @date 2018/10/16  12:28
 * <p>
 * <p>
 * avl平衡树
 */
public class AVLTree implements AbsTree<Integer, String> {

    private Node<Integer, String> root;

    public AVLTree(Integer k, String v) {
        root = new Node<Integer, String>(k, v, 0);
    }


    public String insert(Integer k, String v) {
        Node<Integer, String> node = new Node<Integer, String>(k, v, 0);

        if (root == null) {
            root = node;
            return node.value;
        }

        return insert(node).value;
    }


    private Node<Integer, String> insert(Node<Integer, String> parent, Node<Integer, String> node) {
        if (parent.key == node.key) {
            parent.value = node.value;
        }
        if (parent.key > node.key) {
            if (parent.hasLeft()) {
                insert(parent.left, node);
            } else {
                parent.left = node;
                node.parent = parent.left;
                node.height = parent.height + 1;
            }
        } else {
            if (parent.hasRight()) {
                insert(parent.right, node);
            } else {
                parent.right = node;
                node.parent = parent.right;
            }
        }
        return node;
    }

    //树的高度
    private Integer getMaxH(Node<Integer, String> avlNode) {
        int h = avlNode.height;
        if (avlNode.hasLeft()) {
            h = getMaxH(avlNode.left);
        }
        if (avlNode.hasRight()) {
            h = getMaxH(avlNode.right);
        }
        return h - avlNode.height;
    }

    public String find(Integer integer) {
        return null;
    }

    public Boolean delete(Integer integer) {
        return null;
    }


}
