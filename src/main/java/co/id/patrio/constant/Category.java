package co.id.patrio.constant;

import lombok.Getter;

public enum Category {
    MEDIS("Medis"),
    SEX("Sex &amp; Love"),
    WANITA("Wanita"),
    LIFESYTLE("Lifestyle")
    ;

    @Getter private String medis;

    Category(String medis) {
        this.medis = medis;
    }

    public static Category isCategory(String cat){
        for(int i = 0 ; i < Category.values().length ; i++){
            if(cat.equals(Category.values()[i])){
                return Category.values()[i];
            }
        }
        return null;
    }


}
