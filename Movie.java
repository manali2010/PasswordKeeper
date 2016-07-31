package com.example.user_pc.finalp;

/**
 * Created by user-pc on 29-06-2016.
 */
public class Movie {
    private String name;
    private String user;
    private String pass;

    public Movie() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuser()
    {
        return user;
    }

    public void setuser(String user)
    {
        this.user = user;
    }
    public String getpass()
    {
        return pass;
    }

    public void setpass(String pass)
    {
        this.pass = pass;
    }
}
