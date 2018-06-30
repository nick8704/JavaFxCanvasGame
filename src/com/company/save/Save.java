package com.company.save;

import java.util.ArrayList;
import java.util.List;

public class Save {

    private List<SaveConfiguration> list = new ArrayList<SaveConfiguration>();
    private int activeIndex;

    public Save() {
    }

    public Save(List<SaveConfiguration> list, int activeShapeIndex) {
        this.list = list;
        this.activeIndex = activeShapeIndex;
    }

    public List<SaveConfiguration> getList() {
        return list;
    }

    public void setList(List<SaveConfiguration> list) {
        this.list = list;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveShapeIndex(int activeShapeIndex) {
        this.activeIndex = activeShapeIndex;
    }

}