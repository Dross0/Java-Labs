package StackCalculator;

import Exceptions.MultipleDefinition;
import Exceptions.UnknownDefinition;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StackWithDefinitions {
    private static final Logger logger = Logger.getLogger(StackWithDefinitions.class.getName());

    public StackWithDefinitions(){
        definitionTable = new DefinitionTable();
        stack = new Stack<>();
    }

    public void push(double value){
        stack.push(value);
    }

    public Double pop(){
        return stack.pop();
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public Double peek(){
        return stack.peek();
    }

    public void clear(){
        stack.clear();
    }

    public Double getDefine(String key){
        Double val = null;
        try {
            val = definitionTable.get(key);
        }
        catch (UnknownDefinition ex){
            logger.log(Level.SEVERE, "Exception:", ex);
        }
        return val;

    }

    public void define(String key, Double val) {
        try {
            definitionTable.define(key, val);
        }
        catch (MultipleDefinition ex){
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    private final DefinitionTable definitionTable;
    private final Stack<Double> stack;
}
