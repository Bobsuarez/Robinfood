package com.robinfood.localorderbc.usecases.findallorderbystatusidusecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.OrderResponseDTO;
import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.robinfood.localorderbc.configs.constants.APIConstants.DEFAULT_STRING;
import static com.robinfood.localorderbc.configs.constants.APIConstants.ORIGIN_POS;

@Service
@Slf4j
public class FindAllOrdersByStatusIdUseCase implements IFindAllOrdersByStatusIdUseCase {
    private final IOrderRepository orderRepository;

    public FindAllOrdersByStatusIdUseCase(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderResponseDTO> invoke(Long statusId) throws JsonProcessingException {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateOnly = dateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
        log.info("Repository: GetOrderDetailByIdUseCase, Method: findAllOrdersByStatusId, " +
                "Param_statusId {}, Param_dateOnly {}", statusId, dateOnly);

        List<OrderEntity>listOrderEntity = this.orderRepository
                .findAllByStatusIdAndCreatedAtGreaterThanEqual(statusId, dateOnly);

        List<OrderResponseDTO> listOrderResponseDTO = convertListOrderEntityToListOrderResponseDTO(listOrderEntity);

        log.info("listOrderResponseDTO {} ", listOrderResponseDTO);

        return listOrderResponseDTO;
    }

    private static @NotNull List<OrderResponseDTO> convertListOrderEntityToListOrderResponseDTO(
            List<OrderEntity> listOrderEntity) {
        return listOrderEntity.stream().map(FindAllOrdersByStatusIdUseCase::buildOrderResponseDTO)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static OrderResponseDTO buildOrderResponseDTO(OrderEntity order) {
        JsonNode json = convertDataToJson(order);
        String mobile = null;
        String name = null;
        boolean isDelivery = Boolean.FALSE;
        Boolean  originPos = null;
        Long userLoyaltyStatus = null;
        String orderNumber = null;
        Long brandId = null;
        Long originId = null;
        String orderUuid = DEFAULT_STRING;
        boolean printTicket = Boolean.FALSE;
        boolean orderIsIntegration;

        if(json!=null && json.has("user")){
            JsonNode user = json.get("user");
            mobile = user.get("mobile").asText();
            name = user.get("lastName").asText() + " " + user.get("firstName").asText();
            if(user.has("loyaltyStatus")){
                userLoyaltyStatus = user.get("loyaltyStatus").asLong();
            }

            orderNumber = json.get("orderNumber").asText();
            brandId = json.get("brandId").asLong();
            originId = json.get("originId").asLong();
            orderIsIntegration =  json.get("orderIsIntegration").asBoolean();
            orderUuid = json.get("orderUuid").asText();
            originPos = getOriginPos(json);

            if(json.has("isDelivery")){
                isDelivery = json.get("isDelivery").asBoolean();
            }

            if(Boolean.TRUE.equals(orderIsIntegration) || Boolean.FALSE.equals(originPos)){
                printTicket = Boolean.TRUE;
            }
        }
        return OrderResponseDTO.builder()
                .date(buildDateTimeInString(order.getCreatedAt()))
                .mobile(mobile)
                .name(name)
                .order(orderNumber)
                .orderId(order.getId())
                .status(order.getStatusId())
                .uid(order.getUid())
                .brandLogo("/images/app/logo-brand-" + brandId + ".png")
                .userLoyaltyStatus(userLoyaltyStatus)
                .originId(originId)
                .orderUuid(orderUuid)
                .delivery(isDelivery)
                .printTicket(printTicket)
                .build();
    }

    private static JsonNode convertDataToJson(OrderEntity order) {
        JsonNode jsonNode = null;
        if(order.getData()!=null){
            try {
                ObjectMapper mapper = new ObjectMapper();
                jsonNode = mapper.readTree(order.getData());
            } catch (JsonProcessingException e) {
                log.info(String.valueOf(e));
            }
        }
        return jsonNode;
    }

    private static Boolean getOriginPos(JsonNode json) {
        JsonNode paymentMethods = json.get("paymentMethods");
        return StreamSupport.stream(paymentMethods.spliterator(), false)
                .map(elementNode -> elementNode.get("originId").asLong())
                .anyMatch(ORIGIN_POS::equals);
    }



    @NotNull
    private static String buildDateTimeInString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
