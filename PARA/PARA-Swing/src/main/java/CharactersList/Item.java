package CharactersList;

import javax.swing.*;

public class Item {

    private String name;
    private ImageIcon img;

    public  Item(String name, ImageIcon img){
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public String toString() {
        return name;
    }
}
