package rus.cheremisin.churchsong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rus.cheremisin.churchsong.DAO.ChurchLocationDAO;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.DTO.GeoNames.GeoNamesResponse;
import rus.cheremisin.churchsong.service.ChurchLocationService; //2017623
import rus.cheremisin.churchsong.Util.Utils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChurchLocationServiceGeonames implements ChurchLocationService {
    private ChurchLocationDAO dao;
    private Utils utils = new Utils();
    @Autowired
    public ChurchLocationServiceGeonames(ChurchLocationDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ChurchLocationDTO> fetchAndSaveLocations() {
        RestTemplate rt = new RestTemplate();


        String url = "http://api.geonames.org/children?geonameId=2017623&username=l_efant";
        String responseXML = rt.getForObject(url, String.class);
        List<ChurchLocationDTO> dtoList = new ArrayList<>();
        try {
            GeoNamesResponse response = utils.parseGeoNamesChildrenXML(responseXML);
//            return response.getGeonames();
            return null;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




//    public ChurchLocationDAO getDao() {
//        return dao;
//    }
//
//    public void setDao(ChurchLocationDAO dao) {
//        this.dao = dao;
//    }
}
