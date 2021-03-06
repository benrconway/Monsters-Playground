package com.example.user.fantasyzooapp.animals;

import com.example.user.fantasyzooapp.behaviours.Dangerous;
import com.example.user.fantasyzooapp.behaviours.Flying;

/**
 * Created by user on 22/09/2017.
 */

public class Dragon extends Carnivore implements Flying, Dangerous{
    public Dragon(String name, Enum<Size> size, int value){
        super(name, size, value);
    }

    public String fly(){
        return "The dragon takes a deep breath and launches into the air";
    }

    public int breakOutModifier(){
        int modifier = getSize().ordinal() * 10;
        return modifier;
    }

}
