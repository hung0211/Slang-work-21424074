/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slang_word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author PhiHung
 */
public class AVLTree implements Serializable{
    public Entry root;

    public AVLTree(Entry root) {
        this.root = root;
    }

    public AVLTree() {
        this.root = null;
    }

    public Entry getRoot() {
        return root;
    }

    public void setRoot(Entry root) {
        this.root = root;
    }

    public int getHeight(Entry x) {
        return x == null ? -1 : x.height;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Entry rotateWithLeft(Entry n2) {
        Entry n1 = n2.Left;
        n2.Left = n1.Right;
        n1.Right = n2;
        n2.height = (Integer.max(getHeight(n2.Left), getHeight(n2.Right)) + 1);
        n1.height = (Integer.max(getHeight(n1.Left), getHeight(n2)) + 1);
        return n1;
    }

    private Entry rotateWithRight(Entry n1) {
        Entry n2 = n1.Right;
        n1.Right = n2.Left;
        n2.Left = n1;
        n1.height = (Integer.max(getHeight(n1.Left), getHeight(n1.Right)) + 1);
        n2.height = (Integer.max(getHeight(n2.Right), getHeight(n1)) + 1);
        return n2;
    }

    private Entry doubleWithLeft(Entry n3) {
        n3.Left = rotateWithRight(n3.Left);
        return rotateWithLeft(n3);
    }

    private Entry doubleWithRight(Entry n1) {
        n1.Right = rotateWithLeft(n1.Right);
        return rotateWithRight(n1);
    }

}
