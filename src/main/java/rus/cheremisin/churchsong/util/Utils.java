package rus.cheremisin.churchsong.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rus.cheremisin.churchsong.DTO.GeoNames.GeoNamesResponse;
import rus.cheremisin.churchsong.DTO.HtmlWeb.HtmlWebResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;

public class Utils {

    public GeoNamesResponse parseGeoNamesChildrenXML(String xml) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(GeoNamesResponse.class);
        return (GeoNamesResponse) context.createUnmarshaller().unmarshal(new StringReader(xml));

    }

    public HtmlWebResponse parseWebHtmlJSON(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, HtmlWebResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
