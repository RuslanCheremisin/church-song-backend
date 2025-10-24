package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;

import java.util.List;


public interface ChurchLocationService {
    List<ChurchLocationDTO> fetchAndSaveLocations();
}
