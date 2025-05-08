package com.example.pract12;

import androidx.core.content.FileProvider;

public class MyFileProvider extends FileProvider
{
    public MyFileProvider()
    {
        super(R.xml.shared_files);
    }
}
