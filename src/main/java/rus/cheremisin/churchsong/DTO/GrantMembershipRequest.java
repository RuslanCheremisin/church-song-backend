package rus.cheremisin.churchsong.DTO;

import jakarta.validation.constraints.NotNull;

public record GrantMembershipRequest(@NotNull Long newMemberId) {
}
