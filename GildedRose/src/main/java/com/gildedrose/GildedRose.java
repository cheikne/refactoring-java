package com.gildedrose;

class GildedRose {
    Item[] items;
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
          updateItem(items[i]);
        }
    }
    
    public static void updateValueQuality(Item item,int value){
        item.quality += value;
    }
    public static void updateItem(Item item) {
        switch (item.name) {
          case AGED_BRIE :
            incrementQualityNotMax(item);
            break;
              
          case BACKSTAGE :
            incrementQualityNotMax(item);
            if (item.sellIn < 11) {
              incrementQualityNotMax(item);
            }
            if (item.sellIn < 6) {
              incrementQualityNotMax(item);
            }
            break;
    
          case SULFURAS : break;
          default:
            decrementQualityNotMin(item);
            break;
        }
        
        item.sellIn = item.name.equals(SULFURAS)?item.sellIn:item.sellIn-1;
        
        if (item.sellIn < 0) {
          switch (item.name) {
            case AGED_BRIE :
              incrementQualityNotMax(item);
              break;
            case BACKSTAGE:
              item.quality = 0;
              break;
            case SULFURAS: break;
            default:
              decrementQualityNotMin(item);
              break;
    
          }
        }
      }

    public static void incrementQualityNotMax(Item item) {
        if (item.quality < 50) {
          updateValueQuality(item,1);
        }
      }
    
      public static void decrementQualityNotMin(Item item) {
        if (item.quality > 0) {
            updateValueQuality(item,-1);
        }
      }
    
}