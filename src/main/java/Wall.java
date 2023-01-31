import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


public class Wall implements Structure, CompositeBlock {

    private List<Block> blocks = new ArrayList<>();

    private String color;
    private String material;

    public Wall(Block... blocks) {
        stream(blocks).forEach(this::addBlock);
    }

    public Wall(String color, String material) {
        this.color = color;
        this.material = material;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    private Block findByPredicate(Predicate<Block> predicate) {
        return blocks.stream()
                .flatMap(Block::toStream)
                .filter(predicate)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Block> getBlocks(Predicate<Block> predicate) {
        return (List<Block>) blocks.stream()
                .flatMap(Block::toStream)
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.ofNullable(findByPredicate(c -> color.equals(c.getColor())));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return Collections.unmodifiableList(getBlocks(m -> material.equals((m.getMaterial()))));
    }

    @Override
    public int count() {
        return (int) blocks.stream().flatMap(Block::toStream).count();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public Stream<Block> toStream() {
        return Stream.of(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, material);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wall wall = (Wall) obj;
        return Objects.equals(color, wall.color) &&
                Objects.equals(material, wall.material);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "blocks=" + blocks +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
