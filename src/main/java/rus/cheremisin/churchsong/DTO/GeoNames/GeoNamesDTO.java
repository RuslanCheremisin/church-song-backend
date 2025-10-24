package rus.cheremisin.churchsong.DTO.GeoNames;

import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNamesDTO extends ChurchLocationDTO {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "geonameId")
    private String geonameId;
    @XmlElement(name = "lat")
    private String lat;
    @XmlElement(name = "lng")
    private String lng;

    public GeoNamesDTO(String name, String geonameId, String lat, String lng) {
        this.name = name;
        this.geonameId = geonameId;
        this.lat = lat;
        this.lng = lng;
    }

    public GeoNamesDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
