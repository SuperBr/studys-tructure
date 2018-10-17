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

        return insert(root, node).value;
    }


    private Node<Integer, String> insert(Node<Integer, String> parent, Node<Integer, String> node) {
        if (parent.key == node.key) {
            parent.value = node.value;
        } else if (parent.key > node.key) {
            if (parent.hasLeft()) {
                insert(parent.left, node);
                if (Math.abs(getMaxH(parent.left) - getMaxH(parent.right)) >= 2) {
                    if (parent.left.key < node.key) {
                        LR(parent);
                    } else {
                        LL(parent.left);
                    }

                }
            } else {
                parent.left = node;
                node.parent = parent.left;
                node.setHeight(parent.getHeight() + 1);
            }
        } else {
            if (parent.hasRight()) {
                insert(parent.right, node);
                if (Math.abs(getMaxH(parent.left) - getMaxH(parent.right)) >= 2) {
                    if (parent.right.key > node.key) {
                        RL(parent);
                    } else {
                        RR(parent);
                    }

                }
            } else {
                parent.right = node;
                node.parent = parent.right;
                node.setHeight(parent.getHeight() + 1);
            }
        }
        return node;
    }

    //左旋转
    private void LL(Node<Integer, String> k2) {
        Node<Integer, String> k1 = k2.parent;
        k1.left = k2.right;
        k2.right = k1;
        k2.parent = k1.parent;
        k1.parent = k2;

    }

    //右旋转
    private void RR(Node<Integer, String> k2) {
        Node<Integer, String> k1 = k2.parent;
        k2.left = k1;
        k1.right = k2.left;
        k1.parent = k2;
        k2.parent = k1.parent;

    }

    //左右旋转
    private void LR(Node<Integer, String> k2) {
        Node<Integer, String> k1 = k2.parent;
        Node<Integer, String> k3 = k2.right;
        //1.变为LL
        k1.left = k3;
        k3.parent = k1;

        k3.left = k2;
        k2.parent = k3;

        k2.right = k3.left;
        k3.left.parent = k3;

        //2. LL
        LL(k3);

    }

    //右左旋转
    private void RL(Node<Integer, String> k2) {
        Node<Integer, String> k1 = k2.parent;
        Node<Integer, String> k3 = k2.left;
        //1.变为RR
        k1.left = k3;
        k3.parent = k1;


        k3.right = k2;
        k2.parent = k3;


        k3.right = k2.left;
        k2.left.parent = k2;

        RR(k3);


    }

    //树的高度
    private Integer getMaxH(Node<Integer, String> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        int h = avlNode.getHeight();
        if (avlNode.hasLeft()) {
            h = getMaxH(avlNode.left);
        }
        if (avlNode.hasRight()) {
            h = getMaxH(avlNode.right);
        }
        return h - avlNode.getHeight();
    }

    public String find(Integer integer) {
        return null;
    }

    public Boolean delete(Integer integer) {
        return null;
    }




    public static void main(String[] args){

        AVLTree avlTree = new AVLTree(28, "28");
        avlTree.insert(26, "26");
        avlTree.insert(20, "20");
        avlTree.insert(48, "48");
        avlTree.insert(2, "2");
        avlTree.insert(17, "17");
        avlTree.insert(18, "18");
        avlTree.insert(19, "19");
        avlTree.insert(20, "20");
    }


}
