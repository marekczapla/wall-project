import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {

    Wall block1 = new Wall("red", "brick");
    Wall block2 = new Wall("blue", "brick");
    Wall block3 = new Wall("blue", "grout");
    Wall block4 = new Wall("grey", "grout");


    private Wall emptyWall;
    private Wall builtWall;

    @BeforeEach
    void setUp() {
        emptyWall = new Wall();
        builtWall = new Wall(block1, block2, block3, block4);
    }

    @Test
    void shouldReturnOptionalEmptyWhenFindBlockByColorIsEmpty() {
        assertEquals(emptyWall.findBlockByColor("none"), Optional.empty());
    }

    @Test
    void shouldReturnEmptyListWhenFindBlockByMaterialIsEmpty() {
        assertEquals(emptyWall.findBlocksByMaterial("none"), Collections.emptyList());
    }

    @Test
    void shouldReturn0WhenWallIsEmpty() {
        assertEquals(emptyWall.count(), 0);
    }

    @Test
    void shouldReturnOptionalBlockOfColorIsNotFound() {
        assertEquals(builtWall.findBlockByColor("none"), Optional.empty());
    }

    @Test
    void shouldReturnEmptyListWhenBlockOfMaterialIsNotFound() {
        assertEquals(builtWall.findBlocksByMaterial("none"), Collections.emptyList());
    }

    @Test
    void shouldFindAnyBlockByColor() {
        assertEquals(builtWall.findBlockByColor("red"), Optional.of(block1));
    }

    @Test
    void shouldFindAllBlocksByMaterial() {
        assertEquals(builtWall.findBlocksByMaterial("grout"), List.of(block3, block4));
    }

    @Test
    void shouldCountAllElements() {
        assertEquals(builtWall.count(), 4);
    }
}
