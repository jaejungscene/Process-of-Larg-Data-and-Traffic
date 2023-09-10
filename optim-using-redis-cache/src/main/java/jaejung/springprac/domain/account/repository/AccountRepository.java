package jaejung.springprac.domain.account.repository;

import jaejung.springprac.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
//    @Modifying(clearAutomatically = true)
//    @Query("update Account a set a.money=a.money+:amount where a.id=:id")
//    int plusMoneyById(Long id, Long amount);
}
