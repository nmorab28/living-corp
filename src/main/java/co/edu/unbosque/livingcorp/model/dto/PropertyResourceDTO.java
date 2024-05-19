package co.edu.unbosque.livingcorp.model.dto;

public class PropertyResourceDTO {

    private int proResId;
    private ResourceDTO resId;
    private PropertyDTO proId;
    private double minPrice;
    private int minTimeH;
    private String availabily;
    private int capacity;
    private String bookEmail;

    public PropertyResourceDTO(int proResId, ResourceDTO resId, PropertyDTO proId, double minPrice, int minTimeH, String availabily, int capacity, String bookEmail) {
        this.proResId = proResId;
        this.resId = resId;
        this.proId = proId;
        this.minPrice = minPrice;
        this.minTimeH = minTimeH;
        this.availabily = availabily;
        this.capacity = capacity;
        this.bookEmail = bookEmail;
    }

    public PropertyResourceDTO() {
        resId = new ResourceDTO();
        proId = new PropertyDTO();
    }

    public int getProResId() {
        return proResId;
    }

    public void setProResId(int proResId) {
        this.proResId = proResId;
    }

    public ResourceDTO getResId() {
        return resId;
    }

    public void setResId(ResourceDTO resId) {
        this.resId = resId;
    }

    public PropertyDTO getProId() {
        return proId;
    }

    public void setProId(PropertyDTO proId) {
        this.proId = proId;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getMinTimeH() {
        return minTimeH;
    }

    public void setMinTimeH(int minTimeH) {
        this.minTimeH = minTimeH;
    }

    public String getAvailabily() {
        return availabily;
    }

    public void setAvailabily(String availabily) {
        this.availabily = availabily;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBookEmail() {
        return bookEmail;
    }

    public void setBookEmail(String bookEmail) {
        this.bookEmail = bookEmail;
    }
}
