package com.maxwell.classcreator.model;

import java.util.List;

/**
 *
 * @author Maxwell Knoxx - 06/2019
 */
public class Input {

    private String text;
    private String path;
    private String className;
    private List<String> fields;
    private Boolean createEntity;
    private Boolean createModel;
    private Boolean createServiceImpl;
    private Boolean createRepository;

    /**
     * @return the fields
     */
    public List<String> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the createEntity
     */
    public Boolean getCreateEntity() {
        return createEntity;
    }

    /**
     * @param createEntity the createEntity to set
     */
    public void setCreateEntity(Boolean createEntity) {
        this.createEntity = createEntity;
    }

    /**
     * @return the createModel
     */
    public Boolean getCreateModel() {
        return createModel;
    }

    /**
     * @param createModel the createModel to set
     */
    public void setCreateModel(Boolean createModel) {
        this.createModel = createModel;
    }

    /**
     * @return the createServiceImpl
     */
    public Boolean getCreateServiceImpl() {
        return createServiceImpl;
    }

    /**
     * @param createServiceImpl the createServiceImpl to set
     */
    public void setCreateServiceImpl(Boolean createServiceImpl) {
        this.createServiceImpl = createServiceImpl;
    }

    /**
     * @return the createRepository
     */
    public Boolean getCreateRepository() {
        return createRepository;
    }

    /**
     * @param createRepository the createRepository to set
     */
    public void setCreateRepository(Boolean createRepository) {
        this.createRepository = createRepository;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

}
