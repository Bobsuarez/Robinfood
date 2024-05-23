package com.robinfood.localorderbc.usecases.findallorderbystatusidusecase;

import com.robinfood.localorderbc.dtos.OrderResponseDTO;
import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllOrdersByStatusIdUseCaseTest {
    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private FindAllOrdersByStatusIdUseCase findAllOrdersByStatusIdUseCase;



    @Test
    void Get_All_Order_By_StatusId_Success() throws Exception {
        List<OrderEntity> list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data(jsonOrderOne()).
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    @Test
    void Get_All_Order_By_StatusId_Validating_Conditions_Json_Success() throws Exception {
        List<OrderEntity>list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data(jsonOrderTwo()).
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    @Test
    void Get_All_Order_By_StatusId_Validating_Conditions_Not_Founds() throws Exception {
        List<OrderEntity>list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data(jsonOrderThere()).
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    @Test
    void Get_All_Order_By_StatusId_When_Json_IsNull() throws Exception {
        List<OrderEntity>list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data(null).
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    @Test
    void Get_All_Order_By_StatusId_When_Json_IsInvalid() throws Exception {
        List<OrderEntity>list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data("json").
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    @Test
    void Get_All_Order_By_StatusId_Validating_Printer_Ticket_IsFalse() throws Exception {
        List<OrderEntity>list = new ArrayList<>();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);


        list.add(OrderEntity
                .builder()
                .id(1L)
                .brandId(1L)
                .createdAt(dateTime)
                .data(jsonOrderFor()).
                build());
        when(orderRepository.findAllByStatusIdAndCreatedAtGreaterThanEqual(1L, dateOnly))
                .thenReturn(list);


        List<OrderResponseDTO>listOrderResponseDTO= this.findAllOrdersByStatusIdUseCase.invoke(1L);

        Assertions.assertNotNull(listOrderResponseDTO);
    }

    private String jsonOrderOne(){
        return "{\"id\": 5475532, \"tax\": 2800.0, \"uid\": \"95z8ye7hzyw4\", \"orderUuid\": \"95z8ye7hzyw4666\", \"user\": {\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\", \"loyaltyStatus\":  1}, \"buyer\": {}, \"notes\": \"POSV2\", \"total\": 37800, \"users\": [{\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\"}], \"flowId\": 5, \"brandId\": 9, \"coupons\": [], \"invoice\": \"28337\", \"printed\": false, \"storeId\": 27, \"co2Total\": 0, \"currency\": \"COP\", \"discount\": 0, \"originId\": 10, \"products\": [{\"id\": 1437, \"sku\": \"12277544897157791797\", \"name\": \"MUY Paisa\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\", \"taxes\": [{\"id\": 9375208, \"price\": 1029.6296, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 13900.0, \"groups\": [{\"id\": 10, \"sku\": \"12277329449820618983\", \"name\": \"Ingrediente..\", \"portions\": [{\"id\": 1350, \"sku\": \"12277329449820618983\", \"name\": \"Arroz blanco\", \"price\": 0.0, \"units\": 1, \"weight\": 148.0, \"addition\": false, \"discount\": 0, \"parentId\": 88, \"quantity\": 1}, {\"id\": 1371, \"sku\": \"12277329449820619163\", \"name\": \"Fríjol rojo\", \"price\": 0.0, \"units\": 1, \"weight\": 107.0, \"addition\": false, \"discount\": 0, \"parentId\": 97, \"quantity\": 1}, {\"id\": 1380, \"sku\": \"12277329449820619172\", \"name\": \"Chicharrón\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 110, \"quantity\": 1}, {\"id\": 1374, \"sku\": \"12277329449820619166\", \"name\": \"Carne molida a la criolla\", \"price\": 0.0, \"units\": 1, \"weight\": 78.0, \"addition\": false, \"discount\": 0, \"parentId\": 2325, \"quantity\": 1}, {\"id\": 1381, \"sku\": \"12277329449820619173\", \"name\": \"Chorizo\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 108, \"quantity\": 1}, {\"id\": 264, \"sku\": \"17680480075943772449\", \"name\": \"Plátano Maduro\", \"price\": 0.0, \"units\": 1, \"weight\": 50.0, \"addition\": false, \"discount\": 0, \"parentId\": 104, \"quantity\": 1}, {\"id\": 11, \"sku\": \"26257095296811284\", \"name\": \"Hogao\", \"price\": 0.0, \"units\": 1, \"weight\": 40.0, \"addition\": false, \"discount\": 0, \"parentId\": 2318, \"quantity\": 1}, {\"id\": 58, \"sku\": \"26257095296811331\", \"name\": \"Jugo\", \"price\": 0.0, \"units\": 6, \"weight\": 266.0, \"addition\": false, \"discount\": 0, \"parentId\": 416, \"quantity\": 1}], \"removedPortions\": []}], \"sizeId\": 1, \"brandId\": 1, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"MUY\", \"articleId\": 1412, \"basePrice\": 13900.0, \"brandName\": \"MUY\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 13900.0, \"categoryId\": 3, \"brandMenuId\": 1, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}, {\"id\": 1392, \"sku\": \"12277329449820619212\", \"name\": \"Pizza Colombiana\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/6ef82490-db6d-4216-8e7a-88406ec6788d.png\", \"taxes\": [{\"id\": 9375209, \"price\": 1770.3704, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 23900.0, \"groups\": [], \"sizeId\": 13, \"brandId\": 4, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"Mediana\", \"articleId\": 1351, \"basePrice\": 23900.0, \"brandName\": \"Pixi\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 23900.0, \"categoryId\": 3, \"brandMenuId\": 3, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}], \"statusId\": 1, \"subtotal\": 35000.0, \"brandName\": \"Robinfood\", \"deduction\": 0, \"discounts\": [], \"storeName\": \"MUY 83\", \"originName\": \"POS\", \"statusName\": \"Pedido\", \"orderNumber\": \"5707\", \"transactionId\": 2347720, \"deliveryTypeId\": 1, \"paymentMethods\": [{\"id\": 1, \"tax\": 2800.0, \"value\": 37800, \"discount\": 0.0, \"originId\": 10, \"subtotal\": 35000.0}], \"orderIsIntegration\": true, \"isDelivery\": true}";
    }

    private String jsonOrderTwo(){
        return "{\"id\": 5475532, \"tax\": 2800.0, \"uid\": \"95z8ye7hzyw4\", \"orderUuid\": \"95z8ye7hzyw4666\", \"buyer\": {}, \"notes\": \"POSV2\", \"total\": 37800, \"users\": [{\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\"}], \"flowId\": 5, \"brandId\": 9, \"coupons\": [], \"invoice\": \"28337\", \"printed\": false, \"storeId\": 27, \"co2Total\": 0, \"currency\": \"COP\", \"discount\": 0, \"originId\": 10, \"products\": [{\"id\": 1437, \"sku\": \"12277544897157791797\", \"name\": \"MUY Paisa\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\", \"taxes\": [{\"id\": 9375208, \"price\": 1029.6296, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 13900.0, \"groups\": [{\"id\": 10, \"sku\": \"12277329449820618983\", \"name\": \"Ingrediente..\", \"portions\": [{\"id\": 1350, \"sku\": \"12277329449820618983\", \"name\": \"Arroz blanco\", \"price\": 0.0, \"units\": 1, \"weight\": 148.0, \"addition\": false, \"discount\": 0, \"parentId\": 88, \"quantity\": 1}, {\"id\": 1371, \"sku\": \"12277329449820619163\", \"name\": \"Fríjol rojo\", \"price\": 0.0, \"units\": 1, \"weight\": 107.0, \"addition\": false, \"discount\": 0, \"parentId\": 97, \"quantity\": 1}, {\"id\": 1380, \"sku\": \"12277329449820619172\", \"name\": \"Chicharrón\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 110, \"quantity\": 1}, {\"id\": 1374, \"sku\": \"12277329449820619166\", \"name\": \"Carne molida a la criolla\", \"price\": 0.0, \"units\": 1, \"weight\": 78.0, \"addition\": false, \"discount\": 0, \"parentId\": 2325, \"quantity\": 1}, {\"id\": 1381, \"sku\": \"12277329449820619173\", \"name\": \"Chorizo\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 108, \"quantity\": 1}, {\"id\": 264, \"sku\": \"17680480075943772449\", \"name\": \"Plátano Maduro\", \"price\": 0.0, \"units\": 1, \"weight\": 50.0, \"addition\": false, \"discount\": 0, \"parentId\": 104, \"quantity\": 1}, {\"id\": 11, \"sku\": \"26257095296811284\", \"name\": \"Hogao\", \"price\": 0.0, \"units\": 1, \"weight\": 40.0, \"addition\": false, \"discount\": 0, \"parentId\": 2318, \"quantity\": 1}, {\"id\": 58, \"sku\": \"26257095296811331\", \"name\": \"Jugo\", \"price\": 0.0, \"units\": 6, \"weight\": 266.0, \"addition\": false, \"discount\": 0, \"parentId\": 416, \"quantity\": 1}], \"removedPortions\": []}], \"sizeId\": 1, \"brandId\": 1, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"MUY\", \"articleId\": 1412, \"basePrice\": 13900.0, \"brandName\": \"MUY\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 13900.0, \"categoryId\": 3, \"brandMenuId\": 1, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}, {\"id\": 1392, \"sku\": \"12277329449820619212\", \"name\": \"Pizza Colombiana\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/6ef82490-db6d-4216-8e7a-88406ec6788d.png\", \"taxes\": [{\"id\": 9375209, \"price\": 1770.3704, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 23900.0, \"groups\": [], \"sizeId\": 13, \"brandId\": 4, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"Mediana\", \"articleId\": 1351, \"basePrice\": 23900.0, \"brandName\": \"Pixi\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 23900.0, \"categoryId\": 3, \"brandMenuId\": 3, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}], \"statusId\": 1, \"subtotal\": 35000.0, \"brandName\": \"Robinfood\", \"deduction\": 0, \"discounts\": [], \"storeName\": \"MUY 83\", \"originName\": \"POS\", \"statusName\": \"Pedido\", \"orderNumber\": \"5707\", \"transactionId\": 2347720, \"deliveryTypeId\": 1, \"paymentMethods\": [{\"id\": 1, \"tax\": 2800.0, \"value\": 37800, \"discount\": 0.0, \"originId\": 11, \"subtotal\": 35000.0}], \"orderIsIntegration\": false}";
    }

    private String jsonOrderThere(){
        return "{\"id\": 5475532, \"tax\": 2800.0, \"uid\": \"95z8ye7hzyw4\", \"orderUuid\": \"95z8ye7hzyw4666\", \"user\": {\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\"}, \"buyer\": {}, \"notes\": \"POSV2\", \"total\": 37800, \"users\": [{\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\"}], \"flowId\": 5, \"brandId\": 9, \"coupons\": [], \"invoice\": \"28337\", \"printed\": false, \"storeId\": 27, \"co2Total\": 0, \"currency\": \"COP\", \"discount\": 0, \"originId\": 10, \"products\": [{\"id\": 1437, \"sku\": \"12277544897157791797\", \"name\": \"MUY Paisa\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\", \"taxes\": [{\"id\": 9375208, \"price\": 1029.6296, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 13900.0, \"groups\": [{\"id\": 10, \"sku\": \"12277329449820618983\", \"name\": \"Ingrediente..\", \"portions\": [{\"id\": 1350, \"sku\": \"12277329449820618983\", \"name\": \"Arroz blanco\", \"price\": 0.0, \"units\": 1, \"weight\": 148.0, \"addition\": false, \"discount\": 0, \"parentId\": 88, \"quantity\": 1}, {\"id\": 1371, \"sku\": \"12277329449820619163\", \"name\": \"Fríjol rojo\", \"price\": 0.0, \"units\": 1, \"weight\": 107.0, \"addition\": false, \"discount\": 0, \"parentId\": 97, \"quantity\": 1}, {\"id\": 1380, \"sku\": \"12277329449820619172\", \"name\": \"Chicharrón\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 110, \"quantity\": 1}, {\"id\": 1374, \"sku\": \"12277329449820619166\", \"name\": \"Carne molida a la criolla\", \"price\": 0.0, \"units\": 1, \"weight\": 78.0, \"addition\": false, \"discount\": 0, \"parentId\": 2325, \"quantity\": 1}, {\"id\": 1381, \"sku\": \"12277329449820619173\", \"name\": \"Chorizo\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 108, \"quantity\": 1}, {\"id\": 264, \"sku\": \"17680480075943772449\", \"name\": \"Plátano Maduro\", \"price\": 0.0, \"units\": 1, \"weight\": 50.0, \"addition\": false, \"discount\": 0, \"parentId\": 104, \"quantity\": 1}, {\"id\": 11, \"sku\": \"26257095296811284\", \"name\": \"Hogao\", \"price\": 0.0, \"units\": 1, \"weight\": 40.0, \"addition\": false, \"discount\": 0, \"parentId\": 2318, \"quantity\": 1}, {\"id\": 58, \"sku\": \"26257095296811331\", \"name\": \"Jugo\", \"price\": 0.0, \"units\": 6, \"weight\": 266.0, \"addition\": false, \"discount\": 0, \"parentId\": 416, \"quantity\": 1}], \"removedPortions\": []}], \"sizeId\": 1, \"brandId\": 1, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"MUY\", \"articleId\": 1412, \"basePrice\": 13900.0, \"brandName\": \"MUY\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 13900.0, \"categoryId\": 3, \"brandMenuId\": 1, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}, {\"id\": 1392, \"sku\": \"12277329449820619212\", \"name\": \"Pizza Colombiana\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/6ef82490-db6d-4216-8e7a-88406ec6788d.png\", \"taxes\": [{\"id\": 9375209, \"price\": 1770.3704, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 23900.0, \"groups\": [], \"sizeId\": 13, \"brandId\": 4, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"Mediana\", \"articleId\": 1351, \"basePrice\": 23900.0, \"brandName\": \"Pixi\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 23900.0, \"categoryId\": 3, \"brandMenuId\": 3, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}], \"statusId\": 1, \"subtotal\": 35000.0, \"brandName\": \"Robinfood\", \"deduction\": 0, \"discounts\": [], \"storeName\": \"MUY 83\", \"originName\": \"POS\", \"statusName\": \"Pedido\", \"orderNumber\": \"5707\", \"transactionId\": 2347720, \"deliveryTypeId\": 1, \"paymentMethods\": [{\"id\": 1, \"tax\": 2800.0, \"value\": 37800, \"discount\": 0.0, \"originId\": 10, \"subtotal\": 35000.0}], \"orderIsIntegration\": false}";
    }

    private String jsonOrderFor(){
        return "{\"id\": 5475532, \"tax\": 2800.0, \"uid\": \"95z8ye7hzyw4\", \"orderUuid\": \"95z8ye7hzyw4666\", \"user\": {\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\", \"loyaltyStatus\":  1}, \"buyer\": {}, \"notes\": \"POSV2\", \"total\": 37800, \"users\": [{\"id\": 55318, \"email\": \"muy83-c1@muy.com.co\", \"mobile\": \"3831234561\", \"lastName\": \"Muy Calle 83\", \"firstName\": \"Cajero 1\"}], \"flowId\": 5, \"brandId\": 9, \"coupons\": [], \"invoice\": \"28337\", \"printed\": false, \"storeId\": 27, \"co2Total\": 0, \"currency\": \"COP\", \"discount\": 0, \"originId\": 10, \"products\": [{\"id\": 1437, \"sku\": \"12277544897157791797\", \"name\": \"MUY Paisa\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/133a0208-f365-4f21-a09f-b31d3e741e71.png\", \"taxes\": [{\"id\": 9375208, \"price\": 1029.6296, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 13900.0, \"groups\": [{\"id\": 10, \"sku\": \"12277329449820618983\", \"name\": \"Ingrediente..\", \"portions\": [{\"id\": 1350, \"sku\": \"12277329449820618983\", \"name\": \"Arroz blanco\", \"price\": 0.0, \"units\": 1, \"weight\": 148.0, \"addition\": false, \"discount\": 0, \"parentId\": 88, \"quantity\": 1}, {\"id\": 1371, \"sku\": \"12277329449820619163\", \"name\": \"Fríjol rojo\", \"price\": 0.0, \"units\": 1, \"weight\": 107.0, \"addition\": false, \"discount\": 0, \"parentId\": 97, \"quantity\": 1}, {\"id\": 1380, \"sku\": \"12277329449820619172\", \"name\": \"Chicharrón\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 110, \"quantity\": 1}, {\"id\": 1374, \"sku\": \"12277329449820619166\", \"name\": \"Carne molida a la criolla\", \"price\": 0.0, \"units\": 1, \"weight\": 78.0, \"addition\": false, \"discount\": 0, \"parentId\": 2325, \"quantity\": 1}, {\"id\": 1381, \"sku\": \"12277329449820619173\", \"name\": \"Chorizo\", \"price\": 0.0, \"units\": 1, \"weight\": 30.0, \"addition\": false, \"discount\": 0, \"parentId\": 108, \"quantity\": 1}, {\"id\": 264, \"sku\": \"17680480075943772449\", \"name\": \"Plátano Maduro\", \"price\": 0.0, \"units\": 1, \"weight\": 50.0, \"addition\": false, \"discount\": 0, \"parentId\": 104, \"quantity\": 1}, {\"id\": 11, \"sku\": \"26257095296811284\", \"name\": \"Hogao\", \"price\": 0.0, \"units\": 1, \"weight\": 40.0, \"addition\": false, \"discount\": 0, \"parentId\": 2318, \"quantity\": 1}, {\"id\": 58, \"sku\": \"26257095296811331\", \"name\": \"Jugo\", \"price\": 0.0, \"units\": 6, \"weight\": 266.0, \"addition\": false, \"discount\": 0, \"parentId\": 416, \"quantity\": 1}], \"removedPortions\": []}], \"sizeId\": 1, \"brandId\": 1, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"MUY\", \"articleId\": 1412, \"basePrice\": 13900.0, \"brandName\": \"MUY\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 13900.0, \"categoryId\": 3, \"brandMenuId\": 1, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}, {\"id\": 1392, \"sku\": \"12277329449820619212\", \"name\": \"Pizza Colombiana\", \"image\": \"https://assets.robinfood.com/delivery/menu/products/6ef82490-db6d-4216-8e7a-88406ec6788d.png\", \"taxes\": [{\"id\": 9375209, \"price\": 1770.3704, \"value\": 8.0, \"taxTypeId\": 1, \"taxTypeName\": \"Impoconsumo CO\", \"familyTypeId\": 1}], \"total\": 23900.0, \"groups\": [], \"sizeId\": 13, \"brandId\": 4, \"co2Total\": 0, \"discount\": 0, \"quantity\": 1, \"sizeName\": \"Mediana\", \"articleId\": 1351, \"basePrice\": 23900.0, \"brandName\": \"Pixi\", \"deduction\": 0, \"discounts\": [], \"unitPrice\": 23900.0, \"categoryId\": 3, \"brandMenuId\": 3, \"categoryName\": \"PLATOS PRINCIPALES\", \"articleTypeId\": 1}], \"statusId\": 1, \"subtotal\": 35000.0, \"brandName\": \"Robinfood\", \"deduction\": 0, \"discounts\": [], \"storeName\": \"MUY 83\", \"originName\": \"POS\", \"statusName\": \"Pedido\", \"orderNumber\": \"5707\", \"transactionId\": 2347720, \"deliveryTypeId\": 1, \"paymentMethods\": [{\"id\": 1, \"tax\": 2800.0, \"value\": 37800, \"discount\": 0.0, \"originId\": 11, \"subtotal\": 35000.0}], \"orderIsIntegration\": false, \"isDelivery\": true}";
    }
}
