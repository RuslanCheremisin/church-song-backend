package rus.cheremisin.churchsong.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelMembershipRequest {
    private Long memberId;
}
