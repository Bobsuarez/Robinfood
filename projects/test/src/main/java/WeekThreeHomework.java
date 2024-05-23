//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//@Slf4j
//public class WeekThreeHomework {
//
//    private static final Double SIZE_EQUIPMENT = 20.0;
//    private static Double totalEquipment = 0.0;
//
//    private static final List<Item> items = new ArrayList<>();
//
//    private static final Map<Integer, Map<String, Double>> objectListWithPriority = new HashMap<>();
//
//    public static void main(String[] args) throws Exception {
//
//
//
////      showClient();
//
//        getDataFullMap();
//
//
//
//        Map<Integer, Integer> listToProcess = processToCalculateItems();
//
//
//
//        List<String> resultItem = new ArrayList<>();
//
//        orderListItem.forEach(index -> {
//
//            var list = objectListWithPriority.get(index.getKey());
//
//            var nuevoList = processTotalPriorityV2(list);
//
//            resultItem.addAll(nuevoList);
//
//        });
//
//        log.info("items to pack ---> {}", resultItem);
//
//        log.info("Final luggage size {}", totalEquipment);
//    }
//
//    /**
//     * Method that carries out the ordering of items to take away
//     *
//     * @param listToOrder list of items to order
//     * @return list with order
//     */
//
//
//    /**
//     * Method that carries out the process of selecting articles according to their priority
//     *
//     * @return items list
//     */
//    private static Map<Integer, Integer> processToCalculateItems() {
//
//
//    }
//
//    private static int processTotalPriorityV3(Integer keyPriority, Map<String, Double> item) {
//        int countItem = 0;
//        double totalSumItem = totalEquipment;
//
//        List<String> nameList = new ArrayList<>();
//        for (Map.Entry<String, Double> itemValue : item.entrySet()) {
//            double calculateSum = totalSumItem + itemValue.getValue();
//            if (calculateSum > SIZE_EQUIPMENT) {
//                continue;
//            }
//            totalSumItem = calculateSum;
//            countItem = countItem + 1;
//            nameList.add(itemValue.getKey());
//            log.info("count : {}", countItem);
//            log.info("totalSumItem : {}", totalSumItem);
//            log.info("item add name : {} size ---> {}", itemValue.getKey(), itemValue.getValue());
//        }
//        totalEquipment = totalSumItem;
//        return keyPriority * countItem;
//    }
//
//    private static int processTotalPriority(Integer keyPriority, Map<String, Double> item) {
//
//        int countItem = 0;
//
////        double totalSumItem = 0.0;
////        for (Map.Entry<String, Double> itemValue : item.entrySet()) {
////            double calculateSum = totalSumItem + itemValue.getValue();
////            if (calculateSum > SIZE_EQUIPMENT) {
////                continue;
////            }
////            totalSumItem = calculateSum;
//
//            countItem = countItem + 1;
//
//            log.info("count : {}", countItem);
//            log.info("totalSumItem : {}", totalSumItem);
//        }
//        return keyPriority * countItem;
//    }
//
//
//
//    private static void showClient() {
//
////
////        log.info("Do you want to add new articles? (true o false) ");
////        boolean isAdded = scanner.nextBoolean();
////
////        if (!isAdded) {
////            return;
////        }
////
////        log.info("Cuantos articulos desea ingresar ? ");
////        int itemAdd = scanner.nextInt();
//        addItem();
//    }
//
//    private static void addItem() {
//        Scanner scanner = new Scanner(System.in);
//        log.info("agregue los articulos de la siguiente manera....");
//        log.info("prioridad , nombre , tamaño");
//        for (int i = 1; ; i++) {
//            log.info("Artículo {} ", i);
//            String articleEntered = scanner.nextLine();
//            if (articleEntered.equalsIgnoreCase("fin")) {
//                break;
//            }
//            addItemToList(articleEntered);
//        }
//    }
//
//    private static void addItemToList(String textLine) {
//        String[] itemProcess = textLine.split(",");
//        int priorityEntered = Integer.parseInt(itemProcess[0]);
//        String nameEntered = itemProcess[1];
//        double sizeEntered = Double.parseDouble(itemProcess[2]);
//        items.add(Item.builder().priority(priorityEntered).name(nameEntered).size(sizeEntered).build());
//    }
//
//    private static void getDataFullMap() {
//
//
//    }
//}
//
//
