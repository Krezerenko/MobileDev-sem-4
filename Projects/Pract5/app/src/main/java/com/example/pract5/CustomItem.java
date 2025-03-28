package com.example.pract5;

public class CustomItem
{
    private final String text;
    private final int imageId;

    public CustomItem(String text, int imageId)
    {
        this.text = text;
        this.imageId = imageId;
    }

    public String getText()
    {
        return text;
    }

    public int getImageId()
    {
        return imageId;
    }
}
