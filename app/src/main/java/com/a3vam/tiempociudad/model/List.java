package com.a3vam.tiempociudad.model;

import java.util.ArrayList;

/**
 * Created by rony_2 on 27/11/2016.
 */
public class List
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private Coord coord;

    public Coord getCoord() { return this.coord; }

    public void setCoord(Coord coord) { this.coord = coord; }

    private Main main;

    public Main getMain() { return this.main; }

    public void setMain(Main main) { this.main = main; }

    private int dt;

    public int getDt() { return this.dt; }

    public void setDt(int dt) { this.dt = dt; }

    private Wind wind;

    public Wind getWind() { return this.wind; }

    public void setWind(Wind wind) { this.wind = wind; }

    private Sys sys;

    public Sys getSys() { return this.sys; }

    public void setSys(Sys sys) { this.sys = sys; }

    private Clouds clouds;

    public Clouds getClouds() { return this.clouds; }

    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    private ArrayList<Weather> weather;

    public ArrayList<Weather> getWeather() { return this.weather; }

    public void setWeather(ArrayList<Weather> weather) { this.weather = weather; }
}