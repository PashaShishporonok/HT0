import java.util.ArrayList;
import java.util.List;

public class Room {
    public static final Integer WINDOW_ILLUMINATION = 700;
    public static final Integer MAX_ILLUMINATION = 4000;
    public static final Integer MIN_ILLUMINATION = 300;
    public static final Integer MAX_ROOM_FILLING_PERCENT = 70;

    private boolean isRoomValid = false;
    private String name;
    private Double area, minFreeArea = 0.0, maxFreeArea = 0.0;
    private Integer windowQuantity;
    private Integer illumination = 0;
    private List<Lamp> lampList = new ArrayList<>();
    private List<Furniture> furnitureList = new ArrayList<>();
    private List<String> errorList = new ArrayList<>();

    public Double calculateMaxFurnitureArea() {
        Double sumFurnitureArea = 0.0;
        if (furnitureList.size() > 0) {
            for (Furniture furniture : furnitureList) {
                sumFurnitureArea += furniture.getMaxArea();
            }
        }
        return sumFurnitureArea;
    }

    public Double calculateMinFurnitureArea() {
        Double sumFurnitureArea = 0.0;
        if (furnitureList.size() > 0) {
            for (Furniture furniture : furnitureList) {
                sumFurnitureArea += furniture.getMinArea();
            }
        }
        return sumFurnitureArea;
    }

    public List<Lamp> getLampList() {
        return lampList;
    }

    public Integer getWindowQuantity() {
        return windowQuantity;
    }

    public Integer getIllumination() {
        return illumination;
    }

    public String getName() {
        return name;
    }

    public Room() {
    }

    public boolean isRoomValid() {
        return isRoomValid;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public Room(String name, Double area, Integer windowQuantity) {
        if (windowQuantity * WINDOW_ILLUMINATION > MAX_ILLUMINATION) {
            try {
                throw new IlluminanceTooMuchException("Превышено допустимое значение освещенности в комнате " + name + "\n" +
                        "Значение освещенности не должно превышать " + MAX_ILLUMINATION + " лк.\n" +
                        "Пожалуйста, укажите количество окон в помещении " + name + " менее " + MAX_ILLUMINATION / WINDOW_ILLUMINATION + ".");
            } catch (IlluminanceTooMuchException e) {
                errorList.add(e.getMessage());
            }
        } else {
            this.name = name;
            this.area = area;
            this.windowQuantity = windowQuantity;
            this.illumination = windowQuantity * WINDOW_ILLUMINATION;
            this.isRoomValid = true;
        }
    }

    public Room(String name, Integer area, Integer windowQuantity) {
        if (windowQuantity * WINDOW_ILLUMINATION > MAX_ILLUMINATION) {
            try {
                throw new IlluminanceTooMuchException("Превышено допустимое значение освещенности в комнате " + name + "\n" +
                        "Значение освещенности не должно превышать " + MAX_ILLUMINATION + " лк.\n" +
                        "Пожалуйста, укажите количество окон в помещении " + name + " менее " + MAX_ILLUMINATION / WINDOW_ILLUMINATION + ".");
            } catch (IlluminanceTooMuchException e) {
                errorList.add(e.getMessage());
            }
        } else {
            this.illumination = windowQuantity * WINDOW_ILLUMINATION;
            this.name = name;
            this.area = (double) area;
            this.windowQuantity = windowQuantity;
            this.isRoomValid = true;
        }
    }

    public void add(Lamp lamp) {
        lampList.add(lamp);
        illumination += lamp.getIllumination();
    }

    public void add(Furniture furniture) {
        if (isRoomValid) {
            furnitureList.add(furniture);
            if (minFreeArea != 0.0) {
                minFreeArea -= furniture.getMaxArea();
            } else {
                minFreeArea = area - furniture.getMaxArea();
            }
            if (maxFreeArea != 0.0) {
                maxFreeArea -= furniture.getMinArea();
            } else {
                maxFreeArea = area - furniture.getMinArea();
            }
            if ((100 * (area - minFreeArea) / area) > MAX_ROOM_FILLING_PERCENT) {           // если площадь мебели в комнате превышает заданный предел - задаем невалидность комнаты
                isRoomValid = false;
                try {
                    throw new SpaceUsageTooMuchException("Общая площадь мебели в комнате " + name +
                            " превышает допустимую (" + MAX_ROOM_FILLING_PERCENT + "% от всей площади комнаты) " +
                            "и равен " + Math.round(100 * (area - minFreeArea) / area) + "%");
                } catch (SpaceUsageTooMuchException e) {
                    errorList.add(e.getMessage());
                }
            }
        }
    }

    public Double getArea() {
        return area;
    }

    public Double getMinFreeArea() {
        if (furnitureList.size() > 0) {
            return minFreeArea;
        }
        return area;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public Double getMaxFreeArea() {
        return maxFreeArea;
    }
}
