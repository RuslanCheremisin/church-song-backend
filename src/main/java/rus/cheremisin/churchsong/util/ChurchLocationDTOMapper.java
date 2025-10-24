package rus.cheremisin.churchsong.util;

import org.mapstruct.Mapper;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.entity.church.ChurchLocation;

//@Mapper
public interface ChurchLocationDTOMapper {

    ChurchLocationDTO ChurchLocationToDTO(ChurchLocation location);

    ChurchLocation DTOToChurchLocation(ChurchLocationDTO dto);
}
