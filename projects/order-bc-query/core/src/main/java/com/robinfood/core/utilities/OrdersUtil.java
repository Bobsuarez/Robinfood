package com.robinfood.core.utilities;

import com.robinfood.core.entities.OrderEntity;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrdersUtil {

    public static List<Long> getIdsOrders(List<OrderEntity> orderList) {
        return Optional.ofNullable(orderList)
                .stream().flatMap(Collection::stream)
                .map(OrderEntity::getId)
                .collect(Collectors.toList());
    }

    public static <K, V> List<Long> findAndGroupSegmentIds(Map<K, List<V>> listMapCurrent, Map<K, List<V>> listMapLastWeek) {
        List<Long> idsContainerSearch = new ArrayList<>();
        listMapCurrent.forEach(
                (idEntityCurrent, valueEntityCurrent) -> idsContainerSearch.add((Long) idEntityCurrent)
        );
        listMapLastWeek.forEach(
                (idEntityLastWeek, valueEntityLastWeek) -> idsContainerSearch.add((Long) idEntityLastWeek)
        );
        return removeDuplicate(idsContainerSearch);
    }

    public static BigDecimal sumTotalOrders(Map<Long, List<OrderEntity>> dataList, Long idCompany) {
        return Optional.ofNullable(dataList.get(idCompany))
                .orElseGet(Collections::emptyList)
                .stream()
                .map(orderEntityObj -> BigDecimal.valueOf(orderEntityObj.getTotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static <K, V> Map<K, List<V>> groupedMultipleByEntityAttribute(Collection<V> listEntry, Function<V, K> classifier) {

        if (CollectionUtils.isEmpty(listEntry)) {
            return Collections.emptyMap();
        }
        return listEntry.stream().collect(
                Collectors.groupingBy(classifier, Collectors.mapping(Function.identity(), Collectors.toList())));
    }

    private static List<Long> removeDuplicate(List<Long> listWithDuplicates) {
        return listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
