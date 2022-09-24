package com.gildedrose;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.MatcherAssert.assertEquals;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertThat(element.name, is("foo"));
  }
  @Test
  @DisplayName("Test that the value Quality becomes negative")
  void TestQualityIsNegative(){
    Item element = new Item("MaAXIME",0,20);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    Assertions.assertFalse(element.quality<0);
  }
  @Test
   @DisplayName("Test that the final value of Quality is different at value actual")
  void TestEqualityValueActualEndQuality(){
    Item element = new Item("MaAXIME",0,20);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    Assertions.assertEquals(18,element.quality,()->"Les valeur sont differentes");
  }

}
