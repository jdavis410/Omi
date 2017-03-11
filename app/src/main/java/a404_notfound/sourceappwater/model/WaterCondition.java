package a404_notfound.sourceappwater.model;

import java.util.List;

/**
 * Created by Michelle on 2/27/2017.
 *
 * enum that
 */

public enum WaterCondition {
    WASTE("Waste"),
    CLEAR("Treatable-Clear"),
    MUDDY("Treatable-Muddy"),
    POTABLE("Potable");

    private final String rep;

    /**
     * Constructor
     *
     * @param rep the name of the enum
     */
    WaterCondition(String rep) {
        this.rep = rep;
    }

    /**
     * Getter for the name of the enum
     *
     * @return the string representation of the enum
     */
    public String getRep() {
        return rep;
    }

    /**
     * @return the string representation of the enum
     */
    public String toString() {
        return rep;
    }

}