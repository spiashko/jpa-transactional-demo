package com.siarhei.jpaefficiencyexperiments.web.rest;

import com.siarhei.jpaefficiencyexperiments.bankaccount.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BankAccountResource {

    private final BankAccountManagementService managementService;
    private final BankAccountSearchService searchService;

    @PostMapping("/bank-accounts")
    public ResponseEntity<BankAccountViewAModel> createBankAccount(
            @RequestBody BankAccountCreationModel creationModel) throws URISyntaxException {
        BankAccountViewAModel result = managementService.createBankAccount(creationModel);
        return ResponseEntity.created(new URI("/bank-accounts/" + result.getId()))
                .body(result);
    }

    @GetMapping("/bank-accounts")
    public ResponseEntity<List<BankAccountViewAModel>> getAllViewA() {
        List<BankAccountViewAModel> result = searchService.findAll(BankAccountViewAModel.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bank-accounts/{id}")
    public ResponseEntity<BankAccountViewAModel> getOneViewA(
            @PathVariable UUID id) {
        BankAccountViewAModel result = searchService.findOneOrThrow(id, BankAccountViewAModel.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bank-accounts-viewb/{id}")
    public ResponseEntity<BankAccountViewBModel> getOneViewB(
            @PathVariable UUID id) {
        BankAccountViewBModel result = searchService.findOneOrThrow(id, BankAccountViewBModel.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bank-accounts-viewb")
    public ResponseEntity<List<BankAccountViewBModel>> getAllViewB() {
        List<BankAccountViewBModel> result = searchService.findAll(BankAccountViewBModel.class);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/bank-accounts/{id}")
    public ResponseEntity<Void> deleteBankAccount(
            @PathVariable UUID id) {
        managementService.deleteBankAccountById(id);
        return ResponseEntity.ok().build();
    }

}
