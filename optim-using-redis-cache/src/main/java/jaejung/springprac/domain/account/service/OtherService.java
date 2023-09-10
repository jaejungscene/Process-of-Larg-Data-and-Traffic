package jaejung.springprac.domain.account.service;

import jaejung.springprac.domain.account.Account;
import jaejung.springprac.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OtherService {
    private final AccountRepository accountRepository;

    @Transactional
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void plusMoney(Long id, Long amount){
        Account account = accountRepository.findById(id).get();
        if(account != null) {
            account.plusMoney(amount);
            accountRepository.save(account);
        }
    }

    @Transactional
    public void minusMoney(Long id, Long amount){
        Account account = accountRepository.findById(id).get();
        if(account != null) {
            account.minusMoney(amount);
            accountRepository.save(account);
        }
    }
}
