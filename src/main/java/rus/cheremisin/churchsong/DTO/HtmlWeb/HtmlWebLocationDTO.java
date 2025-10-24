package rus.cheremisin.churchsong.DTO.HtmlWeb;

import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;

public class HtmlWebLocationDTO extends ChurchLocationDTO {
    private Long id;
    private String name;
    private Integer area;
    private String telcod;
    private Double latitude;
    private Double longitude;
    private Integer time_zone;
    private String tz;
    private String english;
    private Integer rajon;
    private Integer sub_rajon;
    private String country;
    private String sound;
    private Integer level;
    private String iso;
    private Integer vid;
    private String post;
    private Long geonameid;
    private String wiki;

    public HtmlWebLocationDTO(Long id,
                              String name,
                              Integer area,
                              String telcod,
                              Double latitude,
                              Double longitude,
                              Integer time_zone,
                              String tz,
                              String english,
                              Integer rajon,
                              Integer sub_rajon,
                              String country,
                              String sound,
                              Integer level,
                              String iso,
                              Integer vid,
                              String post,
                              Long geonameid,
                              String wiki) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.telcod = telcod;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time_zone = time_zone;
        this.tz = tz;
        this.english = english;
        this.rajon = rajon;
        this.sub_rajon = sub_rajon;
        this.country = country;
        this.sound = sound;
        this.level = level;
        this.iso = iso;
        this.vid = vid;
        this.post = post;
        this.geonameid = geonameid;
        this.wiki = wiki;
    }

    public HtmlWebLocationDTO() {
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

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getTelcod() {
        return telcod;
    }

    public void setTelcod(String telcod) {
        this.telcod = telcod;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(Integer time_zone) {
        this.time_zone = time_zone;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Integer getRajon() {
        return rajon;
    }

    public void setRajon(Integer rajon) {
        this.rajon = rajon;
    }

    public Integer getSub_rajon() {
        return sub_rajon;
    }

    public void setSub_rajon(Integer sub_rajon) {
        this.sub_rajon = sub_rajon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(Long geonameid) {
        this.geonameid = geonameid;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }


}
