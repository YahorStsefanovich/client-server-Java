package by.bsuir.stephanovich.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XmlCollection implements Serializable {

    public List<Object> list;

    public XmlCollection(){
        list = new ArrayList<>();
    }

    public void add(Object p){
        list.add(p);
    }
}
