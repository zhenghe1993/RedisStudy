package com.jmper.solr;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-18 0:16:18)
 */
public class Solr implements Serializable {

    @Field("id")
    private int id;
    @Field("programId")
    private int programId;
    @Field("programTitle")
    private String programTitle;
    @Field("searchCount")
    private int searchCount;
    @Field("programType")
    private int type;
    public int getProgramId() {
        return programId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
