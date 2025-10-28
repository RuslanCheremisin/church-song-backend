package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rus.cheremisin.churchsong.DTO.RoleDTO;
import rus.cheremisin.churchsong.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toDto(Role role);

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDTO dto);

    List<RoleDTO> toDtoList(List<Role> roles);

    @Mapping(target = "id", ignore = true)
    Role mergeToEntity(RoleDTO dto, @MappingTarget Role role);


}
