package by.bsuir.stephanovich.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("XmlColllection")
public class XmlCollection {
    @XStreamAsAttribute
    @XStreamAlias("list of Objects")
    public List<Object> list;

    public XmlCollection(){
        list = new ArrayList<>();
    }

    public void add(Object p){
        list.add(p);
    }
}
