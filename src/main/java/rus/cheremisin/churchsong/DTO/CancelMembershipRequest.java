package rus.cheremisin.churchsong.DTO;

import jakarta.validation.constraints.NotNull;

public record CancelMembershipRequest(@NotNull Long memberId) {

}
