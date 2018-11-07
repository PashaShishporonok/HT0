import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private String name;
    private List<Room> roomList = new ArrayList<>();
    private List<String> errorList = new ArrayList<>();

    public Building(String name) {
        this.name = name;
    }


    public void addRoom(String roomName, Double area, Integer windowQuantity) {
        roomList.add(new Room(roomName, area, windowQuantity));
    }

    public void addRoom(String roomName, Integer area, Integer windowQuantity) {
        roomList.add(new Room(roomName, area, windowQuantity));
    }

    public Room getRoom(String name) {
        for (Room room : roomList) {
            if (name.equals(room.getName())) {
                return room;
            }
        }
        return new Room();
    }


    public void describe() {
        System.out.println(name);
        for (Room room : roomList) {
            if(room.getErrorList().size()>0){
                errorList.addAll(room.getErrorList());
            }
            if (room.isRoomValid()) {
                if (room.getIllumination() >= Room.MIN_ILLUMINATION) {
                    printIllumination(room);
                    printArea(room);
                    printFurniture(room);
                } else {
                    try {
                        throw new IlluminanceTooMuchException("Значение освещенности в комнате " + room.getName() +
                                " ниже минимального требования (" + Room.MIN_ILLUMINATION +
                                " лк) и составляет " + room.getIllumination() + " лк." + "\n" +
                                "Пожалуйста, добавте количество окон в помещении " + name +
                                " или установите дополнительные лампочки суммарной освещенностью не менее " +
                                (Room.MIN_ILLUMINATION - room.getIllumination()) + " лк."
                        );
                    } catch (IlluminanceTooMuchException e) {
                        errorList.add(e.getMessage());
                    }
                }
            }
        }
        if(errorList.size() > 0){
            System.out.println();
            System.out.println("В ходе выполнения программы были выявлены следующие несоответствия требованиям:");
            for (String message : errorList) {
                System.out.println(message);
            }
        }
    }

    private void printFurniture(Room room) {
        if (room.isRoomValid()) {
            int furnitureQuantity = room.getFurnitureList().size();
            if (furnitureQuantity > 0) {
                System.out.println("  Мебель:");
                for (Furniture furniture : room.getFurnitureList()) {
                    System.out.print("   " + furniture.getName() + " (площадь ");
                    if (Objects.equals(furniture.getMinArea(), furniture.getMaxArea())) {
                        System.out.print(furniture.getMaxArea());
                    } else {
                        System.out.print("от " + furniture.getMinArea() + " м^2 до " + furniture.getMaxArea());
                    }
                    System.out.println(" м^2)");
                }
            } else {
                System.out.println("  Мебели нет.");
            }
        }
    }

    private void printArea(Room room) {
        if (room.isRoomValid()) {
            System.out.print("  Площадь = " + room.getArea() + " м^2 (");
            if (room.getFurnitureList().isEmpty()) {
                System.out.println("свободно 100%)");
            } else {
                if (Objects.equals(room.getMinFreeArea(), room.getMaxFreeArea())) {
                    System.out.println("занято " + room.calculateMinFurnitureArea() +
                            " м^2, свободно " + room.getMinFreeArea() +
                            " м^2 или " + (int) (100 * room.getMinFreeArea() / room.getArea()) + "% площади)");
                } else {
                    System.out.println("занято " + room.calculateMinFurnitureArea() + "-" +
                            room.calculateMaxFurnitureArea() + " м^2, гарантированно свободно " + room.getMinFreeArea() +
                            " м^2 или " + (int) (100 * room.getMinFreeArea() / room.getArea()) + "% площади)");
                }
            }
        }
    }

    private void printIllumination(Room room) {
        if (room.isRoomValid()) {
            System.out.print(" " + room.getName() + "\n" +
                    "  Освещенность = " + room.getIllumination() + " (");
            if (room.getWindowQuantity() == 0) {
                System.out.print("окон нет");
            } else {
                System.out.print(room.getWindowQuantity() + " окна по " + Room.WINDOW_ILLUMINATION + " лк");
            }
            int lampQuantity = room.getLampList().size();
            if (lampQuantity > 0) {
                if (lampQuantity == 1) {
                    System.out.print(", лампочка ");
                } else {
                    System.out.print(", лампочки ");
                }

                for (int i = 0; i < lampQuantity; i++) {
                    System.out.print(room.getLampList().get(i).getIllumination() + " лк");
                    if (i < lampQuantity - 1) {
                        System.out.print(" и ");
                    }
                }
            } else {
                System.out.print(", лампочек нет");
            }
            System.out.println(")");
        }
    }
}

