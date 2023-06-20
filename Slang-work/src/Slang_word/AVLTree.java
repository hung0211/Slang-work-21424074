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

        private Entry insert(String key, List<String> definition, Entry check) {
        if (check == null) {
            check = new Entry(key, definition);
        } else if (key.compareTo(check.key) < 0) {
            check.Left = insert(key, definition, check.Left);
            if (getHeight(check.Left) - getHeight(check.Right) == 2) {
                if (key.compareTo(check.Left.key) < 0) {
                    check = rotateWithLeft(check);
                } else {
                    check = doubleWithLeft(check);
                }
            }
        } else if (key.compareTo(check.key) > 0) {
            check.Right = insert(key, definition, check.Right);
            if (getHeight(check.Right) - getHeight(check.Left) == 2) {
                if (key.compareTo(check.Right.key) > 0) {
                    check = rotateWithRight(check);
                } else {
                    check = doubleWithRight(check);
                }
            }
        } else {
            System.out.println("The key you entered already exists in Slang word.");
            
        }
        check.height = (Integer.max(getHeight(check.Left), getHeight(check.Right)) + 1);
        return check;
    }

    public void insert(String key, List<String> definition) {
        root = insert(key, definition, root);
    }

    public void printTree() {
        printTree(root);

    }

    private void printTree(Entry check) {
        if (check == null) {
            System.out.println("Blank");
            return;
        }
        if (check.Left != null) {
            printTree(check.Left);
        }
        System.out.print(check);
        if (check.Right != null) {
            printTree(check.Right);
        }
    }

     public String saveTree() {
        return saveTree(root);
    }

    private String saveTree(Entry check) {

        String savedlist = check.toString();
        if (check.Left != null) {

            savedlist += saveTree(check.Left);
        }
        if (check.Right != null) {

            savedlist += saveTree(check.Right);
        }
        return savedlist;

    }

    private Entry findByKey(String key, Entry check) {
        if (check == null) {
            return check;
        }
        Entry returned = null;
        if (key.compareTo(check.key) == 0) {
            returned = check;
        } else if (key.compareTo(check.key) < 0) {
            if (check.Left != null) {
                returned = findByKey(key, check.Left);
            }
        } else if (key.compareTo(check.key) > 0) {
            if (check.Right != null) {
                returned = findByKey(key, check.Right);
            }
        }
        return returned;

    }

    public Entry findByKey(String key) {
        return findByKey(key, root);
    }
    
    private List<Entry> findByDefinition(String val, Entry check) {
        if (check == null) {
            return null;
        }
        List<Entry> found = new ArrayList<Entry>();
        if (check.Left != null) {
            found.addAll(findByDefinition(val, check.Left));
        }

        if (check.toString().contains(val) == true) {
            found.add(check);

        }
        if (check.Right != null) {
            found.addAll(findByDefinition(val, check.Right));
        }
        return found;
    }

    public List<Entry> findByDefinition(String val) {
        return findByDefinition(val, root);
    }
    
    public void remove(String x) {

        root = remove(x, root);
    }


    private Entry findMinNode(Entry find) {
        if (find.Left == null) {
            return find;
        } else {
            return findMinNode(find.Left);
        }
    }

    private Entry remove(String key, Entry check) {
        if (check == null) {
            return check;
        } else if (key.compareTo(check.key) < 0) {
            check.Left = remove(key, check.Left);

        } else if (key.compareTo(check.key) > 0) {
            check.Right = remove(key, check.Right);
        } else {
            if (check.Right == null || check.Left == null) {
                Entry temp = null;
                if (check.Left == null) {
                    temp = check.Right;
                } else {
                    temp = check.Left;
                }
                if (temp == null) {
                    check = null;
                } else {
                    check = temp;
                }
            } else {
                Entry temp = findMinNode(check.Right);
                check = temp;
                check.Right = remove(key, check.Right);
            }
        }
        if (check == null) {
            return check;
        }
        if (getHeight(check.Left) - getHeight(check.Right) == 2) {
            Entry leftCheck = check.Left;
            if (getHeight(leftCheck.Left) - getHeight(leftCheck.Right) < 0) {
                check = doubleWithLeft(check);
            } else {
                check = rotateWithLeft(check);
            }
        } else if (getHeight(check.Right) - getHeight(check.Left) == 2) {
            Entry rightCheck = check.Right;
            if (getHeight(rightCheck.Left) - getHeight(rightCheck.Right) > 0) {
                check = doubleWithRight(check);
            } else {
                check = rotateWithRight(check);
            }
        }
        return check;
    }
    public void removeAll(Entry check) {
        if (check != null) {
            removeAll(check.Left);
            remove(check.key);
            removeAll(check.Right);
        }
    }
    
    public int children(Entry root) {
        if (root == null) {
            return 0;
        }
        return root.children + 1;
    }

    public Entry randomNode(Entry root, int rand) {
        if (root == null) {
            return null; // current is chosen,
        }
        if (rand == children(root.Left)) {
            return root;
        }
        if (rand < children(root.Left)) {
            return randomNode(root.Left, rand);
        }
        return randomNode(root.Right, rand - children(root.Left) - 1);
    }

    public Entry randomNode(Entry root) {
        int rand = (int) (Math.random() * (root.height + 1));
        return randomNode(root, rand);
    }
}

