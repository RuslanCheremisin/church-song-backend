package rus.cheremisin.churchsong.Util.impl;

import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.DTO.HtmlWeb.HtmlWebLocationDTO;
import rus.cheremisin.churchsong.model.church.ChurchLocation;


public class HtmlWebDTOMapper  {

//    @Override
    public HtmlWebLocationDTO ChurchLocationToDTO(ChurchLocation location) {
        if (location == null) { return null; }
        HtmlWebLocationDTO dto = new HtmlWebLocationDTO();

        return null;
    }

//    @Override
    public ChurchLocation DTOToChurchLocation(ChurchLocationDTO dto) {
        ChurchLocation location = new ChurchLocation();
        if (dto instanceof HtmlWebLocationDTO) {
            HtmlWebLocationDTO safeDto = (HtmlWebLocationDTO) dto;
            location.setName(safeDto.getName());
            location.setCountry(safeDto.getCountry());
            location.setRegion(safeDto.getArea().toString());
        }

        return location;
    }
}
