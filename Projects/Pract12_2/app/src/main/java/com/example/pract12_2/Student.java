package com.example.pract12_2;

public class Student
{
    private final String name;
    private final String surname;
    private final String group;
    private final int age;

    public Student(String name, String surname, String group, int age)
    {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getGroup()
    {
        return group;
    }

    public int getAge()
    {
        return age;
    }
}
