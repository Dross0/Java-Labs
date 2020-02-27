package StackCalculator;

import Exceptions.MultipleDefinition;
import Exceptions.UnknownDefinition;

import java.util.HashMap;

public class DefinitionTable {
    public DefinitionTable(){
        table = new HashMap<>();
    }

    public void define(String key, Double value) throws MultipleDefinition{
        if (table.containsKey(key)){
            throw new MultipleDefinition("Multiple definition", key, table.get(key), value);
        }
        table.put(key, value);
    }

    public double get(String key) throws UnknownDefinition{
        Double value = table.get(key);
        if (value == null){
            throw new UnknownDefinition("Define before use get", key);
        }
        return value;
    }

    private final HashMap<String, Double> table;
}
