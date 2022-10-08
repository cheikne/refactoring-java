package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GildedRoseTest {

  private Item[] items = new Item[] {new Item("foo", 10, 20)};
  private GildedRose app = new GildedRose(items);

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    app.updateQuality();
    assertThat(items[0].name, is("foo"));
  } 
    /* Tests sur produits normaux */
  @Test
  public void testDecSellInQuality() {
    app.updateQuality();
    assertThat("diminution des valeurs sellIn et quality par le sytème à la fin de chaque journéé",
                items[0].sellIn == 9 && items[0].quality == 19);
                System.out.println();

  }

  @Test
  public void testQualityNonNegative() {
    items[0].quality = 15;
    app.updateQuality();
    assertThat("La qualité n'est pas négative", items[0].quality > 0);

  }

  @Test
  public void testSellInPositiveQualityIsZero() {
    items[0].quality = 0;
    app.updateQuality();
    assertThat("dimininution produit de qualité nulle ", items[0].quality == 0);
  }

  @Test
  public void testSellInNegativeQualityPositive() {
    items[0].quality = 20;
    items[0].sellIn = -10;
    app.updateQuality();
    assertThat("produit qualité positive et sellIn négatif ", items[0].quality < 20);

  }

  @Test
  public void testSellInAndQualityNegative() {
    items[0].quality = -10;
    items[0].sellIn = -10;
    app.updateQuality();
    assertThat("dimininution produit de qualité négative et sellIn négatif ",
                items[0].quality == -10 && items[0].sellIn == -11);
  }

  @Test
  public void testQualityNoMoreThan50() {
    items[0].quality = 30;
    app.updateQuality();
    assertThat("La qualité n'est pas plus de 50", items[0].quality <= 50);
  }


    /**
     * TESTS SUR DU SULFURAS
     */

  @Test
  public void testSulfurasQualityLessThan50SellInLessThan6() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 5, 49)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].sellIn, is(5));
    assertThat(app.items[0].quality, is(49));

  }

  @Test
  public void testSulfurasConstantQuality() {
    items[0].name = "Sulfuras, Hand of Ragnaros";
    items[0].quality = 20;
    items[0].sellIn = 10;
    app.updateQuality();
    assertThat("Sulfuras ne perd pas en qualité", items[0].quality == 20);

  }

  @Test
  void testSulfurasConstantNegativeQuality() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 0, -5)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].quality, is(-5));
  }

  @Test
  public void testSulfurasQualSellInNegative() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -7, -5)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].quality, is(-5));
  }

  @Test
  public void testSulfurasSellInNegQualPositive() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -1, 5)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].quality, is(5));
  }

/**
 * TESTS SUR DU AGED BRIE
 */
  @Test
  public void testAgedBrieQualityEqualsTo50() {
    items[0].name = "Aged Brie";
    items[0].quality = 50;
    app.updateQuality();
    assertThat("Qualité égale à 50 ", items[0].quality == 50);

  }

  @Test
  public void testAgedBrieSellInLessThan6() {
    items[0].name = "Aged Brie";
    items[0].quality = 20;
    items[0].sellIn = 3;
    app.updateQuality();
    assertThat("Aged bried de sellIn 3", items[0].quality == 21 && items[0].sellIn == 2);

  }

  @Test
  public void testAgedBrieSellInLessThan11() {
    items[0].name = "Aged Brie";
    items[0].quality = 20;
    items[0].sellIn = 8;
    app.updateQuality();
    assertThat("Aged Brie moins 10 de sellIn", items[0].quality == 21 && items[0].sellIn == 7);

  }

  @Test
  public void testAgedBrieSellInMoreThan11() {
    items[0].name = "Aged Brie";
    items[0].quality = 20;
    items[0].sellIn = 15;
    app.updateQuality();
    assertThat("Aged Brie plus de 10 de sellIn", items[0].quality == 21 && items[0].sellIn == 14);
  }

  @Test
  public void testAgedBrieQualityGreaterThan50SellInLessThan11() {
    items[0].name = "Aged Brie";
    items[0].quality = 49;
    items[0].sellIn = 8;
    app.updateQuality();
    assertThat("Backstage de 8 de sellIn et 49 de quality", items[0].quality == 50 && items[0].sellIn == 7);

  }

  @Test
  public void testAgedBrieQualityGreaterThan50SellInLessThan6() {
    items[0].name = "Aged Brie";
    items[0].quality = 49;
    items[0].sellIn = 3;
    app.updateQuality();
    assertThat("Backstage de 8 de sellIn et 49 de quality", items[0].quality == 50 && items[0].sellIn == 2);

  }

  @Test
  public void testAgedBrieNegativeSellInQualityMoreThan50() {
    items[0].name = "Aged Brie";
    items[0].quality = 60;
    items[0].sellIn = -10;
    app.updateQuality();
    assertThat("Backstage de  de sellIn et 49 de quality", items[0].quality == 60 && items[0].sellIn == -11);

  }

  @Test
  public void testAgedBrieNegativeSellInQualityLessThan50() {
    items[0].name = "Aged Brie";
    items[0].quality = 30;
    items[0].sellIn = -10;
    app.updateQuality();
    assertThat("Backstage de 30 sellIn et -10 de quality", items[0].quality == 32 && items[0].sellIn == -11);

  }

/**
 * TESTS SUR DU BACKSTAGE
 */

  @Test
  public void testBackStageQualityEqualsTo50() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 50;
    app.updateQuality();
    assertThat("Qualité égale à 50 ", items[0].quality == 50);

  }

  @Test
  public void testBackStageSellInLessThan6() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 25;
    items[0].sellIn = 2;
    app.updateQuality();
    assertThat("Backstage", items[0].quality == 28 && items[0].sellIn == 1);
  }

  @Test
  public void testBackStageSellInf11QualInf50() {
    Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 49)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertThat(app.items[0].quality, is(50));
  }

  @Test
  public void testBackStageSellInLessThan11() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 21;
    items[0].sellIn = 8;
    app.updateQuality();
    assertThat("Backstage 8 de sellIn", items[0].quality == 23 && items[0].sellIn == 7);
  }

  @Test
  public void testBackStageQualityGreaterThan50SellInLessThan11() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 49;
    items[0].sellIn = 8;
    app.updateQuality();
    assertThat("Backstage de 8 de sellIn et 49 de quality", items[0].quality == 50 && items[0].sellIn == 7);
  }

  @Test
  public void testBackStageQualityGreaterThan50SellInLessThan6() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 49;
    items[0].sellIn = 3;
    app.updateQuality();
    assertThat("Backstage de 3 de sellIn et 49 de quality", items[0].quality == 50 && items[0].sellIn == 2);
  }

  @Test
  public void testBkgStageQualityNegativeSellInLessThan6() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = -20;
    items[0].sellIn = 0;
    app.updateQuality();
    assertThat("Backstage de 0 de sellIn et 20 de quality", items[0].quality == 0 && items[0].sellIn == -1);
  }

  @Test
  public void testBckStageSellInNegativeQualityLessThan50() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 20;
    items[0].sellIn = -15;
    app.updateQuality();
    assertThat("Backstage de 0 de sellIn et 20 de quality", items[0].quality == 0);
  }

  @Test
  public void testBckStageQualityMoreThan50() {
    items[0].name = "Backstage passes to a TAFKAL80ETC concert";
    items[0].quality = 70;
    items[0].sellIn = 15;
    app.updateQuality();
    assertThat("Backstage dépassant 50 de qualité", items[0].quality == 70 && items[0].sellIn == 14);
  }
}
