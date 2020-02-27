package Commands;

import Exceptions.EmptyStackException;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {

    @Test
    void execute() throws InvalidNumberOfArguments, EmptyStackException {
        StackWithDefinitions stack = new StackWithDefinitions();
        Pop cmd = new Pop();
        for (int i = 0; i < 100; ++i){
            stack.push(i);
        }
        assertEquals(100, stack.size());
        for (int i = 0; i < 100; ++i){
            cmd.execute(stack);
            assertEquals(100 - i - 1, stack.size());
        }
        assertTrue(stack.isEmpty());
    }
}