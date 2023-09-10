package jaejung.springprac.controller;


import jaejung.springprac.domain.ValidRequestDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validTest")
public class ValidTestController {
    @PostMapping("")
    public void validationtest(@RequestBody @Valid ValidRequestDto requestDto){
        System.out.println("alskjdflj > " + requestDto);
    }
}
