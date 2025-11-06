package rus.cheremisin.churchsong.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserMemberDTO;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.User;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserDTO toDto(User user);

    UserMemberDTO toMemberDTO(User user);

//    @Mapping(target = "id", ignore = true)
    User toEntity(UserDTO dto);

    User fromCreateRequestToEntity(UserCreateRequest request);

    List<UserDTO> toDtoList(List<User> users);
    List<UserMemberDTO> toMemberDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User mergeToEntity(UserDTO dto, @MappingTarget User user);


}
