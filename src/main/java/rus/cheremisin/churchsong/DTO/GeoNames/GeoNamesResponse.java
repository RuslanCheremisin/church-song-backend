package rus.cheremisin.churchsong.DTO.GeoNames;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "geonames")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoNamesResponse extends ChurchLocationResponse {
    @XmlElement(name = "totalResultsCount")
    private Integer totalResultsCount;
    @XmlElement(name = "geoname")
    private List<GeoNamesDTO> geonames;

    public GeoNamesResponse() {
    }

    public GeoNamesResponse(Integer totalResultsCount, List<GeoNamesDTO> geonames) {
        this.totalResultsCount = totalResultsCount;
        this.geonames = geonames;
    }

    public Integer getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(Integer totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<GeoNamesDTO> getGeonames() {
        return geonames;
    }

    public void setGeonames(List<GeoNamesDTO> geonames) {
        this.geonames = geonames;
    }
}
