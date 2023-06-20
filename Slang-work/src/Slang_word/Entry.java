/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Slang_word;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nqs
 */
public class Entry implements Serializable {

    public String key;
    public List<String> definition;
    int height = 0;
    int children = 0;
    Entry Left = null;
    Entry Right = null;

    public Entry() {

    }

    public Entry(String key, List<String> definition) {
        this.key = key;
        this.definition = (definition);
    }

    public Entry(String key) {
        this.key = key;
        this.definition = null;
    }

    public void setRight(Entry x) {
        this.Right = x;
    }
    
    public int getHeight() {
        return height;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getDefinition() {
        return definition;
    }

    public void setDefinition(List<String> definition) {
        this.definition = definition;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String str = key + "`";
        for (int i = 0; i < definition.size(); i++) {
            str = str + definition.get(i);
            if (i + 1 < definition.size()) {
                str = str + "|";
            }
        }
        return str + "\n";
    }

    public void writeLine(String file) {
        try (BufferedWriter saved = new BufferedWriter(new FileWriter(file, true))) {
            saved.write(toString());
        } catch (IOException e) {
            System.out.println("Invalid File name.");
        }
    }

}
