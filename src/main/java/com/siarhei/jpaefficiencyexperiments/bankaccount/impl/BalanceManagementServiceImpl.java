package com.siarhei.jpaefficiencyexperiments.bankaccount.impl;

import com.siarhei.jpaefficiencyexperiments.bankaccount.BalanceManagementService;
import com.siarhei.jpaefficiencyexperiments.bankaccount.BankAccount;
import com.siarhei.jpaefficiencyexperiments.bankaccount.BankAccountSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BalanceManagementServiceImpl implements BalanceManagementService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountSearchService searchService;

    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void addToBalance(UUID id, Long amount) {
        BankAccount customer = searchService.findOneOrThrow(id);
        customer.setBalance(customer.getBalance() + amount);
        bankAccountRepository.save(customer);
    }

    @Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void subtractFromBalance(UUID id, Long amount) {
        BankAccount customer = searchService.findOneOrThrow(id);
        customer.setBalance(customer.getBalance() - amount);
        bankAccountRepository.save(customer);
    }
}
