package rus.cheremisin.churchsong.Util;

import org.mapstruct.Mapper;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.model.church.ChurchLocation;

@Mapper
public interface ChurchLocationDTOMapper {

    ChurchLocationDTO ChurchLocationToDTO(ChurchLocation location);

    ChurchLocation DTOToChurchLocation(ChurchLocationDTO dto);
}
