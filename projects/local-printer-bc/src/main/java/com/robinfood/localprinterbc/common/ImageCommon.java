package com.robinfood.localprinterbc.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageCommon {

    public enum BrandEnum{
        MUY(1L, "MUY", "logo-muy.png"),
        JUST(5L, "JUST", "logo-justburgers.png"),
        NATURAL_SIN(8L, "NATURAL_SIN", "logo-pecado.png"),
        THE_CUT_PIZZA(7L, "THE_CUT_PIZZA", "logo-thecut.png"),
        YINYAMM(12L, "YINYAMM", "logo-yinyamm.png"),
        ORIGINAL(2L, "ORIGINAL", "logo-original.png"),
        PIXI(4L, "PIXI", "logo-pixi.png"),
        ROBIN_FOOD(9L, "ROBIN_FOOD", "logo-robinfood.png"),
        TREMENDO(6L, "TREMENDO", "logo-tremendo.png"),
        TRIBUTO(11L, "TRIBUTO", "logo-tributo.png"),
        SAMBA(17L, "SAMBA", "logo_samba.png"),
        ABUNDANTE(18L, "ABUNDANTE", "logo_abundante_turbo.png"),
        NATURAL_TURBO(19L, "NATURAL_TURBO", "logo-pecado.png"),
        BOWLO_BR(20L, "BOWLO_BR", "logo_bowlo_br.png"),
        HOGAO(21L, "HOGAO", "logo_hogao.png"),
        ABUNDANTE_TURBO(22L, "ABUNDANTE_TURBO", "logo_abundante_turbo.png");


        private Long brandId;
        public String name;
        public String imageName;

        BrandEnum(Long brandId, String name,  String imageName){
            this.brandId = brandId;
            this.name = name;
            this.imageName = imageName;
        }

        public static BrandEnum findByBrandId(Long brandId) {
            for (BrandEnum value : BrandEnum.values()) {
                if (value.getBrandId().equals(brandId)) {
                    return value;
                }
            }
            return BrandEnum.ROBIN_FOOD;
        }

        public static BrandEnum findByName(String name) {
            for (BrandEnum value : BrandEnum.values()) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return BrandEnum.ROBIN_FOOD;
        }

        public Long getBrandId() {
            return brandId;
        }

        public String getName() {
            return name;
        }
    }

    public static BufferedImage getImage(BrandEnum image) throws IOException {
        URL url = getURL(image.imageName);
        return ImageIO.read(url);
    }


    private static URL getURL(String imageName){
        String strPath =  "images/" +  imageName;
        return ImageCommon.class
                .getClassLoader()
                .getResource(strPath);
    }




}
