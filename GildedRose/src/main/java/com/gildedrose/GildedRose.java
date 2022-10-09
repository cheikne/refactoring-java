package com.gildedrose;

class GildedRose {
    Item[] items;
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGEDBRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
          updateItem(items[i]);
        }
    }
    
    public static void setValueQuality(Item item,int value){
        item.quality += value;
    }
    public static void updateItem(Item item) {
        switch (item.name) {
          case AGEDBRIE :
            incrementValueQualityNotMax(item);
            break;
              
          case BACKSTAGE :
            incrementValueQualityNotMax(item);
            if (item.sellIn < 11) {
              incrementValueQualityNotMax(item);
            }
            if (item.sellIn < 6) {
                incrementValueQualityNotMax(item);
            }
            break;
    
          case SULFURAS : break;
          default:
            decrementValueQualityNotMin(item);
            break;
        }
        
        item.sellIn = item.name.equals(SULFURAS)?item.sellIn:item.sellIn-1;
        
        if (item.sellIn < 0) {
          switch (item.name) {
            case AGEDBRIE :
            incrementValueQualityNotMax(item);
              break;
            case BACKSTAGE:
              item.quality = 0;
              break;
            case SULFURAS: break;
            default:
              decrementValueQualityNotMin(item);
              break;
    
          }
        }
      }

    public static void incrementValueQualityNotMax(Item item) {
        if (item.quality < 50) {
          setValueQuality(item,1);
        }
      }
    
      public static void decrementValueQualityNotMin(Item item) {
        if (item.quality > 0) {
            setValueQuality(item,-1);
        }
      }
    
}