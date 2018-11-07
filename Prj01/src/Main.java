public class Main {
    public static void main(String[] args) {
        Building building = new Building("House");
        building.addRoom("Bedroom", 20, 2);
        building.getRoom("Bedroom").add(new Lamp(150));
        building.getRoom("Bedroom").add(new Lamp(1500));
        building.getRoom("Bedroom").add(new Table("Стол ", 3));
        building.getRoom("Bedroom").add(new Table("Стол обеденный", 2, 10));

        building.addRoom("LivingRoom", 300, 4);
        building.getRoom("LivingRoom").add(new Chair("Кресло", 5));

        building.addRoom("LivingRoom2", 200, 3);
        building.getRoom("LivingRoom2").add(new Chair("Кресло-кровать", 7));

        building.addRoom("Toilet", 10.5, 0);
        building.getRoom("Toilet").add(new Lamp(300));

        building.addRoom("Bathroom", 20, 0);
        building.getRoom("Bathroom").add(new Lamp(150));
        building.describe();
    }
}
