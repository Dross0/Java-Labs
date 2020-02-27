package Commands;

import Exceptions.EmptyStackException;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {

    @Test
    void execute() throws InvalidNumberOfArguments, EmptyStackException {
        StackWithDefinitions stack = new StackWithDefinitions();
        Sqrt cmd = new Sqrt();
        for (int i = 0; i < 100; ++i){
            stack.push(i);
            cmd.execute(stack);
            assertEquals(Math.sqrt(i), stack.pop());
        }
        assertTrue(stack.isEmpty());
    }
}