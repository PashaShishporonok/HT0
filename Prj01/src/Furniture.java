abstract class Furniture {
    private String name;
    private Integer maxArea, minArea;

    public String getName() {
        return name;
    }

    public Furniture(String name, Integer area) {
        this.name = name;
        this.maxArea = area;
        this.minArea = area;
    }

    public Furniture(String name, Integer area1, Integer area2) {
        this.name = name;
        this.maxArea = Math.max(area1, area2);
        this.minArea = Math.min(area1, area2);
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public Integer getMinArea() {
        return minArea;
    }
}