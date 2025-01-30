package viscount.command;

import org.junit.jupiter.api.Test;
import viscount.ViscountException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testHandleToggleValidInput() throws ViscountException {
        // Valid input: toggle 1
        Command command = Parser.parse("toggle 1");
        assertTrue(command instanceof ToggleCommand);
        assertEquals("1", ((ToggleCommand) command).indexStr);
    }

    @Test
    public void testHandleToggleInvalidInput_MissingIndex() {
        // Invalid input: toggle without index
        ViscountException exception = assertThrows(ViscountException.class, () -> {
            Parser.parse("toggle");
        });
        assertEquals("Invalid toggle command, please only state one index to toggle", exception.getMessage());
    }

    @Test
    public void testHandleToggleInvalidInput_ExtraArguments() {
        // Invalid input: toggle 1 2
        ViscountException exception = assertThrows(ViscountException.class, () -> {
            Parser.parse("toggle 1 2");
        });
        assertEquals("Invalid toggle command, please only state one index to toggle", exception.getMessage());
    }

    @Test
    public void testHandleToggleInvalidInput_NonNumericIndex() {
        // Invalid input: toggle abc
        ViscountException exception = assertThrows(ViscountException.class, () -> {
            Parser.parse("toggle abc");
        });
        assertEquals("Invalid toggle command, please enter a numerical index", exception.getMessage());
    }
}