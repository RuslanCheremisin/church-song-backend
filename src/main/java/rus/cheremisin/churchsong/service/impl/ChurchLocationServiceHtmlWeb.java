package rus.cheremisin.churchsong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rus.cheremisin.churchsong.DAO.ChurchLocationDAO;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.DTO.HtmlWeb.HtmlWebLocationDTO;
import rus.cheremisin.churchsong.DTO.HtmlWeb.HtmlWebResponse;
import rus.cheremisin.churchsong.service.ChurchLocationService;
import rus.cheremisin.churchsong.entity.church.ChurchLocation;
import rus.cheremisin.churchsong.util.Utils;
import rus.cheremisin.churchsong.util.impl.HtmlWebDTOMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ChurchLocationServiceHtmlWeb implements ChurchLocationService {

    private ChurchLocationDAO dao;

    private Utils utils = new Utils();
    private HtmlWebDTOMapper dtoMapper = new HtmlWebDTOMapper();

    @Autowired
    public ChurchLocationServiceHtmlWeb(ChurchLocationDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ChurchLocationDTO> fetchAndSaveLocations() {
        RestTemplate rt = new RestTemplate();
        String url = "https://htmlweb.ru/geo/api.php?area=47&json&api_key=374baf438ba12c72ff17f345778634b1&perpage=999999";
        HtmlWebResponse htmlWebResponse = rt.getForObject(url, HtmlWebResponse.class);

        List<HtmlWebLocationDTO> recievedDtoList = htmlWebResponse.getLocationDTOMap().values().stream().toList();
        List<ChurchLocationDTO> processedDtoList = new ArrayList<>();
        for (HtmlWebLocationDTO dto : recievedDtoList) {
            dao.save(new ChurchLocation(dto.getName(), dto.getArea().toString(), dto.getCountry()));
            processedDtoList.add(new ChurchLocationDTO(dto.getName()));
        }

        return processedDtoList;
    }


}
