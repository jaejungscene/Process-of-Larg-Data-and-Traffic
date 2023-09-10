package jaejung.springprac;

import jaejung.springprac.domain.account.Account;
import jaejung.springprac.domain.account.repository.AccountRepository;
import jaejung.springprac.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@DataJpaTest
public class AccountDataTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void transferTest() {
        accountRepository.save(Account.builder()
                .name("person01")
                .money(10000L)
                .build());
        accountRepository.save(Account.builder()
                .name("person02")
                .money(10000L)
                .build());
//        accountService.transfer(1L, 2L, 200L);
    }
}
