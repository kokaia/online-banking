package ge.softlab.lessons.onlinebanking.controllers;

import ge.softlab.lessons.onlinebanking.models.TransactionAddModel;
import ge.softlab.lessons.onlinebanking.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<?> addTransaction(@Valid @RequestBody TransactionAddModel data){
        try {
            service.addTransaction(data);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
