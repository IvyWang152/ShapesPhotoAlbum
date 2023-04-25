import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import scrabble.ScrabbleBag;
import scrabble.Tile;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.*;

public class ScrabbleBagTest {
  private Tile a;
  private Tile b;
  private Tile c;
  private Tile d;
  private Tile e;
  private Tile h;
  private Tile q;
  private Tile x;
  private Tile z;
  private Tile blank;
  private Tile nonAlpha;
  private ScrabbleBag bag;
  @Before
  public void setUp() throws Exception {
    a= new Tile("A", 1);
    b= new Tile("b", 3);
    c= new Tile("c", 3);
    d= new Tile("D", 2);
    e= new Tile("E", 1);
    h= new Tile("h", 4);
    q= new Tile("Q", 10);
    x= new Tile("X", 8);
    z= new Tile("z", 10);
    blank = new Tile(" ", 0);
    nonAlpha = new Tile("@", -1);
    bag = new ScrabbleBag();
  }


  @Test
  public void testAddTile() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4);

    List<Tile> tiles = new ArrayList<>();
    tiles.add(a);
    tiles.add(z);
    tiles.add(h);
    tiles.add(new Tile("h", 4));

    assertEquals(tiles, bag.getAllTiles());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddTile1() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddTile2() {
    Tile badZ = new Tile("z", 12);
    bag.addTile(z);
    bag.addTile(badZ);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddTile3() {
    Tile bad = new Tile("Ha", 2);
    bag.addTile(bad);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadAddTile4() {
    Tile bad = new Tile("@", 2);
    bag.addTile(bad);
  }
  @Test
  public void testRemoveTile() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4);

    Tile tile = bag.removeTile("h");
    assertEquals("Failed Remove Tile H", h, tile);
    assertEquals("Failed size check after removal", 3, bag.getAllTiles().size());
    tile = bag.removeTile("a");
    assertEquals("Failed Remove Tile A", a, tile);
    assertNull("Failed null on removal after no more tiles of that letter", bag.removeTile("a"));
  }

  @Test
  public void testGetAllTiles() {
    // currently same as first test, which is okay. Maybe in the future expand this
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4);

    List<Tile> tiles = new ArrayList<>();
    tiles.add(a);
    tiles.add(z);
    tiles.add(h);
    tiles.add(new Tile("h", 4));

    assertEquals(tiles, bag.getAllTiles());

  }

  @Test
  public void testFilterVowels() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4);
    bag.addTile(b);
    bag.addTile(c);
    bag.addTile(d);
    bag.addTile(e);
    bag.addTile(q);
    bag.addTile(x);

    List<Tile> vowels = bag.filter(tile -> tile.isVowel());
    assertTrue("Missing A in vowels filter", vowels.contains(a));
    assertTrue("Missing E in vowels filter", vowels.contains(e));
    assertTrue( 2 == vowels.size());

  }

  @Test
  public void testFilterHA() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4); // TWO "h" tiles in the bag
    bag.addTile(b);
    bag.addTile(c);
    bag.addTile(d);
    bag.addTile(e);
    bag.addTile(q);
    bag.addTile(x);

    List<Tile> tiles = bag.filter(tile -> tile.getLetter().equals("H") || tile.getLetter().equals("A"));
    assertEquals("Wrong size for filter H, A", 3, tiles.size());
    assertTrue(tiles.contains(a));
    assertTrue(tiles.contains(h));
  }

  @Test
  public void testFilterValues() {
    bag.addTile(a);
    bag.addTile(z);
    bag.addTile(h);
    bag.addTile("h", 4);
    bag.addTile(b);
    bag.addTile(c);
    bag.addTile(d);
    bag.addTile(e);
    bag.addTile(q);
    bag.addTile(x);

    List<Tile> tiles = bag.filter(tile -> tile.getValue() > 7);
    assertEquals("Wrong size for filter values > 7", 3, tiles.size());
    assertTrue(tiles.contains(x));
    assertTrue(tiles.contains(q));
    assertTrue(tiles.contains(z));
  }
}