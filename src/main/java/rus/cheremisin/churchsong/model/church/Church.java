package rus.cheremisin.churchsong.model.church;

import jakarta.persistence.*;

@Entity(name = "html_web_church")
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private ChurchLocation churchLocation;

    public Church(String name, ChurchLocation churchLocation) {
        this.name = name;
        this.churchLocation = churchLocation;
    }

    public Church() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChurchLocation getChurchLocation() {
        return churchLocation;
    }

    public void setChurchLocation(ChurchLocation churchLocation) {
        this.churchLocation = churchLocation;
    }
}
