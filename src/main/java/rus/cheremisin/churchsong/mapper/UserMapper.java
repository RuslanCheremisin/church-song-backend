package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User song);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDTO dto);
}
