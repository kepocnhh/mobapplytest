package com.mobapply.test;

public class Settings
{
    private static String base_url;
    public static String getBase_url()
    {
        return base_url;
    }
    public static void setBase_url(String base_url)
    {
        Settings.base_url = base_url;
    }
}