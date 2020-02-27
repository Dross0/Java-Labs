package Commands;
import Exceptions.EmptyStackException;
import Exceptions.InvalidArgumentType;
import Exceptions.InvalidNumberOfArguments;
import Exceptions.NotEnoughArgumentsForBinaryOperation;
import StackCalculator.StackWithDefinitions;

public interface ICommand {
    void execute(StackWithDefinitions stack, String... args) throws InvalidNumberOfArguments, InvalidArgumentType, EmptyStackException, NotEnoughArgumentsForBinaryOperation;
}
