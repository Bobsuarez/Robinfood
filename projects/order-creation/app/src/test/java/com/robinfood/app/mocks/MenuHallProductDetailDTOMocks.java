package com.robinfood.app.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.core.configs.ModelMapperConfig;
import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import org.modelmapper.ModelMapper;

public class MenuHallProductDetailDTOMocks {

  public static ModelMapper modelMapper = new ModelMapperConfig().modelMapper();

  public static String responseJson = "{\n"
      + "    \"id\": 1,\n"
      + "    \"name\": \"MUY Feijoada\",\n"
      + "    \"price\": 19.9,\n"
      + "    \"reference_price\": 19.9,\n"
      + "    \"sku\": \"12276489554839470441\",\n"
      + "    \"image\": \"https://assets.robinfood.com/delivery/menu/products/06504a4c-d088-45b1-9873-2657bc6fff65.jpg\",\n"
      + "    \"banner_image\": null,\n"
      + "    \"discount\": 0,\n"
      + "    \"display_type\": 2,\n"
      + "    \"brand_id\": 12,\n"
      + "    \"brand_name\": \"MUY\",\n"
      + "    \"type\": 1,\n"
      + "    \"type_name\": \"ARTICLE\",\n"
      + "    \"position\": 2,\n"
      + "    \"sticker_id\": -1,\n"
      + "    \"sticker_name\": null,\n"
      + "    \"size_id\": 19,\n"
      + "    \"size_name\": \"MUY\",\n"
      + "    \"article_id\": 432,\n"
      + "    \"parent_id\": 519,\n"
      + "    \"description\": \"Feijão preto com linguiça defumada, carne seca e bacon, arroz branco, pernil de porco, couve refogada, farofa de alho.\",\n"
      + "    \"product_flow_id\": 2,\n"
      + "    \"product_flow_name\": \"Suggested\",\n"
      + "    \"product_flow_description\": \"Este flujo envía la pantalla de sugerido. \",\n"
      + "    \"product_category_id\": 36,\n"
      + "    \"tags\": [],\n"
      + "    \"groups\": [\n"
      + "      {\n"
      + "        \"id\": 101,\n"
      + "        \"name_singular\": \"Ingrediente\",\n"
      + "        \"name_plural\": \"Ingredientes\",\n"
      + "        \"selection_name_singular\": \"¿Quer tirar algo?\",\n"
      + "        \"selection_name_plural\": \"¿Quer tirar algo?\",\n"
      + "        \"position\": 0,\n"
      + "        \"min\": 0,\n"
      + "        \"max\": 8,\n"
      + "        \"free\": 8,\n"
      + "        \"group_type_id\": 1,\n"
      + "        \"subsidy\": 0,\n"
      + "        \"visible\": 1,\n"
      + "        \"sku\": \"12276489554839470439\",\n"
      + "        \"portions\": [\n"
      + "          {\n"
      + "            \"id\": 641,\n"
      + "            \"name\": \"Feijão preto com linguiça, carne seca e bacon\",\n"
      + "            \"parent_id\": 1076,\n"
      + "            \"image\": \"https://files-muy.s3.us-west-1.amazonaws.com/photos/menus/elements/thumbnails/muybr/feijoada.png\",\n"
      + "            \"price\": 0,\n"
      + "            \"weight\": 170,\n"
      + "            \"discount\": 0,\n"
      + "            \"unit\": 1,\n"
      + "            \"position\": 0,\n"
      + "            \"premium_price\": 0,\n"
      + "            \"default\": true,\n"
      + "            \"sku\": \"12276489554839470370\",\n"
      + "            \"selection_type\": \"unique\"\n"
      + "          },\n"
      + "          {\n"
      + "            \"id\": 640,\n"
      + "            \"name\": \"Pernil de porco desfiado\",\n"
      + "            \"parent_id\": 1090,\n"
      + "            \"image\": \"https://files-muy.s3.us-west-1.amazonaws.com/photos/menus/elements/thumbnails/muybr/PernilDePorco.png\",\n"
      + "            \"price\": 0,\n"
      + "            \"weight\": 50,\n"
      + "            \"discount\": 0,\n"
      + "            \"unit\": 1,\n"
      + "            \"position\": 1,\n"
      + "            \"premium_price\": 0,\n"
      + "            \"default\": true,\n"
      + "            \"sku\": \"12276489554839470369\",\n"
      + "            \"selection_type\": \"unique\"\n"
      + "          }\n"
      + "        ]\n"
      + "      }\n"
      + "        ]\n"
      + "      }\n"
      + "    ]\n"
      + "  }\n"
      + "  }\n";

  public static MenuHallProductDetailDTO aMenuHallProductDTO() throws JsonProcessingException {

    MenuHallProductDetailResponse responseMock = new ObjectMapper().readValue(responseJson,
        MenuHallProductDetailResponse.class);

    return modelMapper.map(responseMock, MenuHallProductDetailDTO.class);
  }
}
