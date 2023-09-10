package jaejung.springprac.domain.account;

public record TransferDto(
        Long from,
        Long to,
        Long amount
) {
}
