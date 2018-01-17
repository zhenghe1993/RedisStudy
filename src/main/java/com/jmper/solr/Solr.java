package com.jmper.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 0:16:18)
 */
public class Solr implements Serializable{

    @Field("id")
    private int id;
    @Field("name")
    private String name;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
