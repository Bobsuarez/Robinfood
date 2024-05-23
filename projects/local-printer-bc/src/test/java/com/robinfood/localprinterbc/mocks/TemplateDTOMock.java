package com.robinfood.localprinterbc.mocks;

import com.google.gson.Gson;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;

public class TemplateDTOMock {

    public TemplateDTO templateDTO() {

        String json = "{\n" +
                "  \"groupId\": 1,\n" +
                "  \"groupName\": \"Colombia\",\n" +
                "  \"rules\": [\n" +
                "    {\n" +
                "      \"description\": \"Suggested Products\",\n" +
                "      \"name\": \"suggestedProducts\",\n" +
                "      \"params\": \"{\\\"notInArray\\\": {\\\"categories\\\": [\\\"Bebidas\\\", \\\"Postres\\\", \\\"Galletas\\\"]}}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Drinks and desserts\",\n" +
                "      \"name\": \"drinksAndDesserts\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\"Bebidas y postres\\\", \\\"inArray\\\": {\\\"originId\\\": [4, 2], \\\"categories\\\": [\\\"Bebidas\\\", \\\"Postres\\\", \\\"Galletas\\\"], \\\"drinksAndDessertsGroups\\\": [6, 16, 19, 46, 47, 59, 62, 63, 66, 67, 81, 83, 88, 96, 97, 98, 104, 105, 129, 145, 152, 153, 155, 157, 160, 161, 172, 181, 196, 199, 200, 201, 204, 208, 210, 216, 218, 219, 7, 15, 34, 41, 74, 82, 83, 116, 130, 145, 152, 159, 205, 211, 215, 484, 43, 60]}}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Remove Drinks And Desserts To Suggested Products\",\n" +
                "      \"name\": \"removeDrinksAndDessertsToSuggestedProducts\",\n" +
                "      \"params\": \"{\\\"inArray\\\": {\\\"drinksAndDessertsGroups\\\": [6, 16, 19, 46, 47, 59, 62, 63, 66, 67, 81, 83, 88, 96, 97, 98, 104, 105, 129, 145, 152, 153, 155, 157, 160, 161, 172, 181, 196, 199, 200, 201, 204, 208, 210, 216, 218, 219, 7, 15, 34, 41, 74, 82, 83, 116, 130, 145, 152, 159, 205, 211, 215]}}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Add (+) symbol to the portions\",\n" +
                "      \"name\": \"addProperty\",\n" +
                "      \"params\": \"{\\\"pathTest\\\": \\\"suggestedProducts.*.groups.*.portions.*\\\", \\\"properties\\\": [{\\\"item\\\": {\\\"symbol\\\": \\\"(+)\\\"}, \\\"whereEq\\\": [\\\"addition\\\", true]}]}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Add (+) symbol to the portions\",\n" +
                "      \"name\": \"addProperty\",\n" +
                "      \"params\": \"{\\\"pathTo\\\": \\\"suggestedProducts.*.groups.*.portions.*\\\", \\\"properties\\\": [{\\\"item\\\": {\\\"symbol\\\": \\\"(+)\\\"}, \\\"whereEq\\\": [\\\"addition\\\", true]}]}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Add (-) and (c) symbols to the portions\",\n" +
                "      \"name\": \"addProperty\",\n" +
                "      \"params\": \"{\\\"pathTo\\\": \\\"suggestedProducts.*.groups.*\\\", \\\"properties\\\": [{\\\"item\\\": {\\\"symbol\\\": \\\"(-)\\\"}, \\\"whereIn\\\": [\\\"id\\\", [70, 72, 112, 125, 213]]}, {\\\"item\\\": {\\\"symbol\\\": \\\"(c)\\\"}, \\\"propIn\\\": [\\\"changedPortion\\\"]}], \\\"childPathTo\\\": \\\"portions.*\\\"}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Add copy text to the order\",\n" +
                "      \"name\": \"addProperty\",\n" +
                "      \"params\": \"[{\\\"properties\\\": [{\\\"test\\\": {\\\"copyText\\\": \\\"Copia\\\"}, \\\"whereIn\\\": [\\\"printed\\\", [true]]}]}]\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Group Included portions\",\n" +
                "      \"name\": \"groupIncludedPortions\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\"Ingredientes\\\"}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Group added portions\",\n" +
                "      \"name\": \"groupAddedPortions\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\"Adicionar\\\", \\\"notInArray\\\": {\\\"removalGroups\\\": [70, 72, 112, 125, 213]}}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Group removed portions\",\n" +
                "      \"name\": \"groupRemovedPortions\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\"Quitar\\\"}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Group changed portions\",\n" +
                "      \"name\": \"groupChangedPortions\",\n" +
                "      \"params\": \"{\\\"paths\\\": [\\\"products\\\", \\\"drinksAndDessertsGroups\\\"], \\\"title\\\": \\\"Cambiar\\\"}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Add (-) and (c) symbols to the portions removed\",\n" +
                "      \"name\": \"addProperty\",\n" +
                "      \"params\": \"{\\\"pathTo\\\": \\\"suggestedProducts.*.toRemove.items.*\\\", \\\"properties\\\": [{\\\"item\\\": {\\\"symbol\\\": \\\"(-)\\\"}, \\\"propIn\\\": [\\\"id\\\"]}]}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Remove property drinksAndDessertsGroups\",\n" +
                "      \"name\": \"removeProperty\",\n" +
                "      \"params\": \"{\\\"paths\\\": [\\\"products\\\", \\\"drinksAndDessertsGroups\\\"]}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"to go\",\n" +
                "      \"name\": \"toGo\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\"** Para llevar **\\\", \\\"TogoId\\\": 3}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"setIdAndUser\",\n" +
                "      \"name\": \"setIdAndUser\",\n" +
                "      \"params\": \"{}\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"Origin Title\",\n" +
                "      \"name\": \"originTitle\",\n" +
                "      \"params\": \"{\\\"title\\\": \\\" | Domicilio\\\", \\\"originId\\\": 2, \\\"deliveryTypeId\\\": 2}\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"templateId\": 1,\n" +
                "  \"templateString\": \"<title center>{{kitchenTicketNumber}}</title><br><title center>{{brandName}}</title><barcode>{{orderBrandId}}</barcode><br><title center>{{originName}}</title>{{#if toGo.toGo}}<br><text center size=2>{{toGo.title}}</text>{{/if}}{{#if userAndIdOrder}}<br><text center size=2>{{userAndIdOrder.user}}</text><br><text center size=2>{{userAndIdOrder.id}}</text>{{/if}}<br><hr>{{#each suggestedProducts}}<text bold>{{quantity}}x {{sizeName}} {{name}}</text>{{#if toInclude.items}}<text bold>{{toInclude.title}}</text>{{#each toInclude.items}}<text> {{quantity}} x {{{name}}}</text>{{/each}}{{/if}}{{#if toChange.items}}<text bold>{{toChange.title}}</text>{{#each toChange.items}}<text> {{{symbol}}}{{{name}}} -> {{{changedPortion.name}}}</text>{{/each}}{{/if}}{{#if toRemove.items}}<text bold>{{toRemove.title}}</text>{{#each toRemove.items}}<text> {{{symbol}}}{{{name}}}</text>{{/each}}{{/if}}{{#if toAdd.items}}<text bold>{{toAdd.title}}</text>{{#each toAdd.items}}<text> {{{symbol}}} {{quantity}} x {{{name}}}</text>{{/each}}{{/if}}<hr>{{/each}}{{#if drinksAndDesserts.items}}<br><text bold>{{{drinksAndDesserts.title}}}</text>{{#each drinksAndDesserts.items}}<text> {{quantity}} x {{name}}</text>{{/each}}<br>{{/if}}{/each}}{{#if notes}}<br><text bold>Notas</text><text>{{notes}}</text><br>{{/if}}<expand :char=space>now()[[char]]{{copyText}}</expand><feed>2</feed><cut>\",\n" +
                "  \"templateType\": {\n" +
                "    \"name\": \"Ticket Kitchen\",\n" +
                "    \"slug\": \"ticket_kitchen\",\n" +
                "    \"isPartial\": false\n" +
                "  }\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(json, TemplateDTO.class);
    }
}
