package Commands;

import Exceptions.InvalidArgumentType;
import Exceptions.InvalidNumberOfArguments;
import StackCalculator.StackWithDefinitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {

    @Test
    void execute() throws InvalidArgumentType, InvalidNumberOfArguments {
        StackWithDefinitions stack = new StackWithDefinitions();
        Push cmd = new Push();
        for (int i = 0; i < 100; ++i){
            cmd.execute(stack, "1");
            assertEquals(i + 1, stack.size());
        }
    }
}