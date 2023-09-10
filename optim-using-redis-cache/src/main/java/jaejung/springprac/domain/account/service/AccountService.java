package jaejung.springprac.domain.account.service;

import jaejung.springprac.domain.account.Account;
import jaejung.springprac.domain.account.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final OtherService otherService;


    public void register(Account account){
        accountRepository.save(account);
    }

//    @Transactional
//    public void transferMoney(Long from, Long to, Long amount) {
//        transfer(from, to, amount);
//    }


    @Transactional
    public void transfer(Long from, Long to, Long amount) {
        accountRepository.save(Account.builder()
                                    .name("test01")
                                    .money(10000L)
                                    .build());
        otherService.minusMoney(from, amount);
        otherService.plusMoney(to, amount);
        throw new RuntimeException();
    }



    @Transactional
    public void updateName(Long id) {
        Account account = accountRepository.findById(id).get();

        account.updateName("asd");
        account.plusMoney(100L);
    }


    @PostConstruct
    public void init() throws Exception
    {
        System.out.println(
                "---------------2> %s "
                .formatted(this.getClass().getName()));
//        log.debug("debug");
//        log.warn("warn");
    }
}
