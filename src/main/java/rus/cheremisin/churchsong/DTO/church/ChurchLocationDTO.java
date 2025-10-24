package rus.cheremisin.churchsong.DTO.church;

public class ChurchLocationDTO {
    private String name;

    public ChurchLocationDTO(String name) {
        this.name = name;
    }

    public ChurchLocationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
