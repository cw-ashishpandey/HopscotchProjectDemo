
package com.ashish.hopscotchproject.model.moviedetail;

import java.util.ArrayList;
import java.util.List;

public class AbridgedCast {

    private String name;
    private String id;
    private List<String> characters = new ArrayList<String>();

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The characters
     */
    public List<String> getCharacters() {
        return characters;
    }

    /**
     * 
     * @param characters
     *     The characters
     */
    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }


}
