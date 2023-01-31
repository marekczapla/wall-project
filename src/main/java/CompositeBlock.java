import java.util.List;
import java.util.function.Predicate;

public interface CompositeBlock extends Block{

    List<Block> getBlocks(Predicate<Block> predicate);

    List<Block> getBlocks();
}
