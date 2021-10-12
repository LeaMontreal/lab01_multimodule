package com.wlj.app04_blockedlist;

public class BlockedNumber {
    private int id;
    private String number;

    // 提供空参构造器
    public BlockedNumber() {
    }

    // 提供有参构造器
    public BlockedNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlockedNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
