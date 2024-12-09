package main.java.servicesPOJO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserRoot {
    public Integer page;
    public Integer per_page;
    public Integer total;
    public Integer total_pages;
    public ArrayList<UserData> data;
    public Support support;
}