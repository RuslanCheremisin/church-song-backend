package rus.cheremisin.churchsong.service.church;

import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;

import java.util.List;


public interface ChurchLocationService {
    List<ChurchLocationDTO> fetchAndSaveLocations();
}
