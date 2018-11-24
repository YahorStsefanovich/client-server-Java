package by.bsuir.stephanovich.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XmlCollection implements Serializable {

    public List<?> list;

    public XmlCollection(){
        list = new ArrayList<>();
    }

}
