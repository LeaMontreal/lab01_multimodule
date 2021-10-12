package com.wlj.l03_view;

public class FoodInfo {
    // drawable中对应图片的id值
    private Integer id_icon;
    // TextView中显示的名字
    private String name;
    // TextView中显示的名字
    private String detail;

    public FoodInfo() {
    }

    public FoodInfo(Integer id_icon, String name, String detail) {
        this.id_icon = id_icon;
        this.name = name;
        this.detail = detail;
    }

    public Integer getId_icon() {
        return id_icon;
    }

    public void setId_icon(Integer id_icon) {
        this.id_icon = id_icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "id_icon=" + id_icon +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
