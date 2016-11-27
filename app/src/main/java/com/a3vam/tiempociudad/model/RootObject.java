package com.a3vam.tiempociudad.model;

import java.util.ArrayList;

/**
 * Created by rony_2 on 27/11/2016.
 */

public class RootObject
{
    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private String cod;

    public String getCod() { return this.cod; }

    public void setCod(String cod) { this.cod = cod; }

    private int count;

    public int getCount() { return this.count; }

    public void setCount(int count) { this.count = count; }

    private ArrayList<List> list;

    public ArrayList<List> getList() { return this.list; }

    public void setList(ArrayList<List> list) { this.list = list; }
}