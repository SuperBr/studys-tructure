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


        Node currentNode = root;
        Node parentNode = null;
        while (root.key != null && root.key != key) {
            parentNode = root;
            if (key > root.key) {
                if (root.hasRight()) {
                    currentNode = root.right;
                }
                return Boolean.FALSE;
            }
            if (root.key < root.key) {
                if (root.hasLeft()) {
                    currentNode = root.left;
                }
                return Boolean.FALSE;
            }

        }


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

        }
        //既有左 又有右子树

        Node tmp = getSucceed(currentNode);
        currentNode.key = tmp.key;
        currentNode.value = tmp.value;
        if (tmp.hasRight()) {
            tmp.parent.left = tmp.right;
            tmp.right.parent = tmp.parent;
        }
        return Boolean.TRUE;
    }

    //获取 后继
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

    public static void main(String[] args) {
        SearcherTree searcherTree = new SearcherTree(18, "18");
        searcherTree.insert(12, "12");
        searcherTree.insert(14, "14");
        searcherTree.insert(18, "18");
        searcherTree.insert(3, "3");
        searcherTree.insert(5, "5");
        searcherTree.insert(7, "7");

        System.out.println(searcherTree.find(6));
        System.out.println(searcherTree.find(12));
        searcherTree.delete(12);
        System.out.println(searcherTree.find(12));



    }

}
