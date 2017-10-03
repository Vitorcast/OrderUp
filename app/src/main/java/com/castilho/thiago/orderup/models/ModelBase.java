package com.castilho.thiago.orderup.models;

/**
 * Created by Thiago on 2017-09-26.
 */

public class ModelBase {
    private Integer Id;
    private String Name;
    private Integer ResourceId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getResourceId() {
        return ResourceId;
    }

    public void setResourceId(Integer resourceId) {
        ResourceId = resourceId;
    }

    public ModelBase() {
    }

    public ModelBase(Integer id, String name, Integer resourceId) {
        Id = id;
        Name = name;
        ResourceId = resourceId;
    }
}
