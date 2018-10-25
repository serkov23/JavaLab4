package lab4;

import java.security.InvalidParameterException;
import java.util.Vector;

public class Tree<T extends Comparable<T>> {
    private Node root;

    void add(T val) {
        root = add(root, val);
    }

    int size() {
        return size(root);
    }

    private int size(Node vertex) {
        return vertex == null ? 0 : (size(vertex.left) + size(vertex.right) + 1);
    }

    Vector<Vector<T>> getPyramid(T filler) {
        return pyramid(root, 0, 0, new Vector<>(), filler);
    }

    private Vector<Vector<T>> pyramid(Node vertex, int layer, int index, Vector<Vector<T>> pyr, T filler) {
        if (vertex == null)
            return pyr;
        if (pyr.size() == layer) {
            int sz = (int) Math.pow(2, layer);
            pyr.add(new Vector<>(sz));
            for (int i = 0; i < sz; i++)
                pyr.elementAt(layer).add(filler);
        }
        pyr.elementAt(layer).setElementAt(vertex.val, index);
        pyr = pyramid(vertex.left, layer + 1, index, pyr,filler);
        return pyramid(vertex.right, layer + 1, index + 1, pyr,filler);
    }

    private Node add(Node vertex, T val) {
        if (vertex == null)
            return new Node(val);
        if (val.compareTo(vertex.val) < 0) {
            vertex.left = add(vertex.left, val);
        } else {
            vertex.right = add(vertex.right, val);
        }
        return vertex;
    }

    boolean find(T val) {
        return find(root, val);
    }

    private boolean find(Node vertex, T val) {
        if (vertex == null)
            return false;
        if (vertex.val.equals(val))
            return true;
        return find(val.compareTo(vertex.val) < 0 ? vertex.left : vertex.right, val);
    }

    void delete(T val) {
        root = delete(root, val);
    }

    private Node delete(Node vertex, T val) {
        checkVertex(vertex);
        if (val.equals(vertex.val)) {
            var tmp = deleter(vertex.left, vertex.right);
            try {
                vertex.right = delete(vertex.right, val);
            } catch (InvalidParameterException ignored) {
                return tmp;
            }
            return tmp;
        }
        if (val.compareTo(vertex.val) < 0) vertex.left = delete(vertex.left, val);
        else vertex.right = delete(vertex.right, val);
        return vertex;
    }

    private Node deleter(Node l, Node r) {
        if (l == null)
            return r;
        if (r == null)
            return l;
        l.right = deleter(l.right, r);
        return l;
    }

    private void checkVertex(Node vertex) {
        if (vertex == null)
            throw new InvalidParameterException("Value not found");
    }

    Vector<T> goVLR() {
        return goVLR(root, new Vector<>());
    }

    private Vector<T> goVLR(Node vertex, Vector<T> res) {
        if (vertex == null)
            return res;
        res.add(vertex.val);
        res = goVLR(vertex.left, res);
        res = goVLR(vertex.right, res);
        return res;
    }

    Vector<T> goLVR() {
        return goLVR(root, new Vector<>());
    }

    private Vector<T> goLVR(Node vertex, Vector<T> res) {
        if (vertex == null)
            return res;
        res = goLVR(vertex.left, res);
        res.add(vertex.val);
        res = goLVR(vertex.right, res);
        return res;
    }

    Vector<T> goLRV() {
        return goLRV(root, new Vector<>());
    }

    private Vector<T> goLRV(Node vertex, Vector<T> res) {
        if (vertex == null)
            return res;
        res = goLRV(vertex.left, res);
        res = goLRV(vertex.right, res);
        res.add(vertex.val);
        return res;
    }


    class Node {
        T val;
        Node left, right;

        Node(T val) {
            left = right = null;
            this.val = val;
        }
    }
}
