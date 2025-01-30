package viscount.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoToString() {

        // Test toString() for am incomplete ToDo
        ToDo todo = new ToDo("Description");
        assertEquals("[T] [ ] Description", todo.toString());

        // Test toString() for a completed ToDo
        ToDo completedToDo = new ToDo("Description", true);
        assertEquals("[T] [x] Description", completedToDo.toString());
    }

    @Test
    public void testGetFileRepresentation() {
        // Test file representation for an incomplete ToDo
        ToDo todo = new ToDo("Description");
        assertEquals("T |   | Description", todo.getFileRepresentation(" | "));

        // Test file representation for completed ToDo
        ToDo completedToDo = new ToDo("Description", true);
        assertEquals("T | X | Description", completedToDo.getFileRepresentation(" | "));
    }
}
