package com.wind.coi.entity;

import lombok.Data;


/**
 * @author hsc
 * @date 2024/6/27 10:15
 */
@Data
public class Character {

    private int id;

    private String name;

    private int level;

    private int exp;

    public Character(String name) {
        this.name = name;
        this.level = 0;
        this.exp = 0;
    }
}