import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import scrabble.Tile;
import scrabble.TileAlphabetComparator;
import scrabble.TileValueComparator;

import static org.junit.Assert.*;

public class TileTest {
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
  }

  @Test
  public void testGetters() {
    assertEquals("Wrong letter from getter", "A", a.getLetter());
    assertEquals("Wrong letter from getter", "X", x.getLetter());
    assertEquals("Wrong letter from getter", "H", h.getLetter());
    assertEquals("Wrong letter from getter", " ", blank.getLetter());
    assertEquals("Wrong letter from getter", "@", nonAlpha.getLetter());

    assertEquals("Wrong value from getter", 1, a.getValue());
    assertEquals("Wrong value from getter", 8, x.getValue());
    assertEquals("Wrong value from getter", 4, h.getValue());
    assertEquals("Wrong value from getter", 0, blank.getValue());
    assertEquals("Wrong value from getter", -1, nonAlpha.getValue());
  }


  @Test
  public void isVowel() {
    assertTrue(a.isVowel());
    assertTrue(e.isVowel());
    assertFalse(h.isVowel());
    assertFalse(x.isVowel());
    assertFalse(blank.isVowel());
    assertFalse(nonAlpha.isVowel());
  }

  @Test
  public void testEquals() {
    assertTrue(a.equals(a));
    assertTrue( a == a);
    assertFalse(a.equals(x));
    Tile otherA = new Tile("A", 1);
    assertTrue(a.equals(otherA));
    assertFalse(a == otherA);
    otherA = new Tile("A", 5);
    assertFalse(a.equals(otherA));
  }

  @Test
  public void testHashCode() {
    Tile otherA = new Tile("A", 1);
    assertTrue(a.hashCode() == otherA.hashCode());
    otherA = new Tile("A", 5);
    assertFalse(a.hashCode() == otherA.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(a.toString().equals("A:1"));
  }

  @Test
  public void testValueComparator() {
    List<Tile> tiles = new ArrayList<>();
    List<Tile> sorted = new ArrayList<>();
    tiles.add(z);
    tiles.add(h);
    tiles.add(a);
    tiles.add(c);
    tiles.add(d);
    tiles.add(x);

    sorted.add(a);
    sorted.add(d);
    sorted.add(c);
    sorted.add(h);
    sorted.add(x);
    sorted.add(z);

    assertFalse(tiles.equals(sorted));
    tiles.sort(new TileValueComparator());
    assertTrue(tiles.equals(sorted));
  }

  @Test
  public void testAlphaComparator() {
    List<Tile> tiles = new ArrayList<>();
    List<Tile> sorted = new ArrayList<>();
    tiles.add(z);
    tiles.add(h);
    tiles.add(a);
    tiles.add(b);
    tiles.add(c);
    tiles.add(d);
    tiles.add(e);
    tiles.add(q);
    tiles.add(x);

    // sorted by value
    sorted.add(a);
    sorted.add(b);
    sorted.add(c);
    sorted.add(d);
    sorted.add(e);
    sorted.add(h);
    sorted.add(q);
    sorted.add(x);
    sorted.add(z);

    assertFalse(tiles.equals(sorted));
    tiles.sort(new TileAlphabetComparator());
    assertTrue(tiles.equals(sorted));
  }
}