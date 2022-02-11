package edu.wctc;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class VennDiagram<T> {
    private String label1;
    private String label2;
    private String label3;

    private Set<T> circle1;
    private Set<T> circle2;
    private Set<T> circle3;

    public VennDiagram(String label1, String label2, String label3) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;

        this.circle1 = new HashSet<T>();
        this.circle2 = new HashSet<T>();
        this.circle3 = new HashSet<T>();
    }


    public Set<T> unionOf(String first, String second){
        Set<T> result = new HashSet<T>();
        Set<T> unionOfCircle1 = this.GetCircleForLabel(first);
        Set<T> unionOfCircle2 = this.GetCircleForLabel(second);

        result.addAll(unionOfCircle1);
        result.addAll(unionOfCircle2);

        return result;
    }

     private Set<T> GetCircleForLabel(String label) {
        if (label.equals(this.label1)) {
            return this.circle1;
        } else if (label.equals(this.label2)) {
            return this.circle2;
        } else if (label.equals(this.label3)) {
            return this.circle3;
        }else return null;
    }
    public Set<T> intersectionOf(String first, String second){
        Set<T> result = new HashSet<T>();
        Set<T>  intersectionOfCircle1 = this.GetCircleForLabel(first);
        Set<T> intersectionOfCircle2 = this.GetCircleForLabel(second);

        for(T item: intersectionOfCircle1) {
            if (intersectionOfCircle2.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }
    public Set<T> complementOf(String first, String second){
        Set<T> result = new HashSet<T>();
        Set<T>  complementOfCircle1 = this.GetCircleForLabel(first);
        Set<T> complementOfCircle2 = this.GetCircleForLabel(second);

        for(T item: complementOfCircle1) {
            if (!complementOfCircle2.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public Set<T> diagramCenter (){
        Set<T> result = new HashSet<T>();

        for(T item: this.circle3) {
            if (this.circle1.contains(item) && this.circle2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }


    public void add(T item, String...labels) {
        for(String label : labels){
            this.GetCircleForLabel(label).add(item);
        }
    }
}
