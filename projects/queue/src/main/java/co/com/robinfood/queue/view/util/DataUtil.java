package co.com.robinfood.queue.view.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataUtil {

    private static final int SubListSize = 5;

    public static <T> List<List<T>> splitListData(List<T> toListConvert) {

        return IntStream.range(0, (toListConvert.size() + SubListSize - 1) / SubListSize)
                .mapToObj(i -> toListConvert.subList(i * SubListSize, Math.min(SubListSize * (i + 1), toListConvert.size())))
                .collect(Collectors.toList());

    }
}
