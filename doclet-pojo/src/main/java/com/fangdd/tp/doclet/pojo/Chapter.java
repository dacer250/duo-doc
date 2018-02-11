package com.fangdd.tp.doclet.pojo;

import java.util.List;

public class Chapter extends MongoDbEntity {
    /**
     * 章节名
     */
    private String name;

    /**
     * 书的章节
     */
    private List<Section> sections;

    /**
     * 接口类型：restFul / dubbo / ...
     */
    private String type;

    /**
     * maven.groupId
     */
    private String groupId;

    /**
     * maven.artifactId
     */
    private String artifactId;

    /**
     * maven.version
     */
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {

        return "Chapter: " + name + "[" + type + "]";
    }
}