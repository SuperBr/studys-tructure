package com.tree.searchTree;

import com.tree.AbsTree;
import com.tree.Node;
import sun.rmi.server.InactiveGroupException;

import javax.management.NotificationEmitter;
import java.util.Comparator;

/**
 * @author wangyongchao
 * @date 2018/10/15  15:58
 *
 * 二查搜索数
 *
 * 二叉查找树的时间复杂度：它和二分查找一样，插入和查找的时间复杂度均为O(logn)
 *
 * 但是在最坏的情况下仍然会有O(n)的时间复杂度 （链表）
 *
 *
 *
 *
 *
 *
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

    private String insert(Node<Integer, String> parent, Node<Integer, String> curNode) {

        if (parent.key - curNode.key == 0) {
            parent.value = curNode.value;
            return curNode.value;
        } else if (parent.key > curNode.key) {
            if (parent.hasLeft()) {
                return insert(parent.left, curNode);
            } else {
                parent.left = curNode;
                curNode.parent = parent;
            }

        } else {
            if (parent.hasRight()) {
                return insert(parent.right, curNode);
            } else {
                parent.right = curNode;
                curNode.parent = parent;
            }
        }
        return curNode.value;
    }

    public String find(Integer integer) {
        if (root == null) {
            return null;
        }
        return find(root, integer).value;
    }

    private Node<Integer, String> find(Node<Integer, String> parent, Integer key) {
        if (parent.key - key == 0) {
            return parent;
        } else if (parent.key > key) {
            if (parent.hasLeft()) {
                return find(parent.left, key);
            }
        } else {
            if (parent.hasRight()) {
                return find(parent.right, key);
            }
        }
        return new Node();
    }


    public Boolean delete(Integer key) {


        Node<Integer, String> currentNode = root;
        while (currentNode.key != null && currentNode.key != key) {
            if (key > currentNode.key) {
                if (currentNode.hasRight()) {
                    currentNode = currentNode.right;
                } else {
                    return Boolean.FALSE;
                }

            } else {
                if (currentNode.hasLeft()) {
                    currentNode = currentNode.left;
                } else {
                    return Boolean.FALSE;
                }

            }

        }

        Node<Integer, String> parentNode = currentNode.parent;
        if (currentNode.key == null) {
            return Boolean.FALSE;
        }
        //叶子 节点
        if (!currentNode.hasLeft() && !currentNode.hasLeft()) {
            if (parentNode.left == currentNode) {
                parentNode.left = null;
            }
            if (parentNode.right == currentNode) {
                parentNode.right = null;
            }
            return Boolean.TRUE;
        }

        //只有左右子树
        if (!currentNode.hasLeft() || !currentNode.hasRight()) {
            if (currentNode.hasLeft()) {
                if (parentNode.left == currentNode) {
                    parentNode.left = currentNode.left;
                } else {
                    parentNode.right = currentNode.left;
                }
            } else {

                if (parentNode.left == currentNode) {
                    parentNode.left = currentNode.right;
                } else {
                    parentNode.right = currentNode.right;
                }
            }
            return Boolean.TRUE;

        }
        //既有左 又有右子树

        Node<Integer, String> tmp = getSucceed(currentNode);
        currentNode.key = tmp.key;
        currentNode.value = tmp.value;
        if (tmp.hasRight()) {
            currentNode.right=tmp.right;
            tmp.right.parent=currentNode.right;
        }else {
            currentNode.right=null;
        }
        return Boolean.TRUE;
    }

    //获取 后继

    /**
     * 1. 没有右子树
     * <p>
     * 2.有右子树
     *
     * @param node
     * @return
     */
    private Node<Integer, String> getSucceed(Node<Integer, String> node) {
        Node current;
        if (node.hasRight()) {
            current = node.right;
            while (current != null && current.hasLeft()) {
                current = current.left;
            }
            return current;
        }
        current = node.parent;
        while (current.left != node) {
            node = node.parent;
            current = current.parent;
        }
        return current;
    }


    public void midIterate() {
        midIteratePrint(root);


    }

    private void midIteratePrint(Node<Integer, String> node) {
        if (node.hasLeft()) {
            midIteratePrint(node.left);
        }
        System.out.println(node.key);
        if (node.hasRight()) {
            midIteratePrint(node.right);
        }

    }

    public Node<Integer, String> getMin() {
        return leftNode(root);
    }

    public Node<Integer, String> getMax() {
        return rightNode(root);
    }


    //获取最右节点
    private Node<Integer, String> leftNode(Node<Integer, String> node) {
        Node<Integer, String> minNode=node;
        if (node.hasLeft()) {
            node = node.left;
            minNode = leftNode(node);
        }
        return minNode;
    }

    //获取最左节点
    private Node<Integer, String> rightNode(Node<Integer, String> node) {
        Node<Integer, String> maxNode=node;
        if (node.hasRight()) {
            node = node.right;
            maxNode= rightNode(node);
        }
        return maxNode;
    }

    public static void main(String[] args) {
        SearcherTree searcherTree = new SearcherTree(18, "18");
        searcherTree.insert(12, "12");
        searcherTree.insert(14, "14");
        searcherTree.insert(22, "22");
        searcherTree.insert(3, "3");
        searcherTree.insert(5, "5");
        searcherTree.insert(7, "7");
        searcherTree.insert(28, "28");
        searcherTree.insert(100, "100");
        searcherTree.insert(-18, "-18");
        searcherTree.insert(12, "llllll");
        searcherTree.delete(14);

        System.out.println(searcherTree.find(12));
        searcherTree.delete(12);
        System.out.println(searcherTree.find(12));
        System.out.println(searcherTree.find(7));



        System.out.println("min:" + searcherTree.getMin().key.toString());

        System.out.println("max:" + searcherTree.getMax().key.toString());


        searcherTree.midIterate();

    }

}
