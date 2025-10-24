package rus.cheremisin.churchsong.DTO.HtmlWeb;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationResponse;

import java.util.HashMap;
import java.util.Map;

public class HtmlWebResponse extends ChurchLocationResponse {
    @JsonAnySetter
    private Map<String, HtmlWebLocationDTO> locationDTOMap = new HashMap<>();
    private Integer limit;
    private Double balans;
    private String message;


    public HtmlWebResponse(Map<String, HtmlWebLocationDTO> locationDTOMap, Integer limit, Double balans, String message) {
        this.locationDTOMap = locationDTOMap;
        this.limit = limit;
        this.balans = balans;
        this.message = message;
    }

    public HtmlWebResponse() {
    }

    public Map<String, HtmlWebLocationDTO> getLocationDTOMap() {
        return locationDTOMap;
    }

    public void setLocationDTOMap(Map<String, HtmlWebLocationDTO> locationDTOMap) {
        this.locationDTOMap = locationDTOMap;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Double getBalans() {
        return balans;
    }

    public void setBalans(Double balans) {
        this.balans = balans;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
