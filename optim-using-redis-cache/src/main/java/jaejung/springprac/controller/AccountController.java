package jaejung.springprac.controller;

import jaejung.springprac.domain.account.Account;
import jaejung.springprac.domain.account.TransferDto;
import jaejung.springprac.domain.account.service.AccountService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    final private AccountService accountService;

    @GetMapping("")
    public void Transfer(
            @RequestParam("from") Long from,
            @RequestParam("to") Long to,
            @RequestParam("amount") Long amount
    ){
        accountService.transfer(from, to, amount);
    }

    @PostMapping("/save")
    public void register(@RequestBody Account acc){
        System.out.println(acc);
        accountService.register(acc);
    }

//    @GetMapping("/plus")
//    public void plusMoney(
//            @RequestParam("id") Long id,
//            @RequestParam("amount") Long amount){
//        accountService.plusMoney(id, amount);
//    }

    @GetMapping("/name")
    public void changeName(
            @RequestParam Long id
    ) {
        accountService.updateName(id);
    }

    @PostConstruct
    public void init() throws Exception
    {
        System.out.println(
                "---------------0> %s "
                .formatted(this.getClass().getName()));
        System.out.println(
                "---------------1> %s "
                .formatted(this.accountService.getClass().getName()));
    }
}
