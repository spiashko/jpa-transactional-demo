package com.siarhei.jpatransactionaldemo.web.rest;

import com.siarhei.jpatransactionaldemo.moneytransfer.MoneyTransfer;
import com.siarhei.jpatransactionaldemo.moneytransfer.MoneyTransferFilter;
import com.siarhei.jpatransactionaldemo.moneytransfer.MoneyTransferManagementService;
import com.siarhei.jpatransactionaldemo.moneytransfer.MoneyTransferSearchService;
import com.siarhei.jpatransactionaldemo.web.dto.moneytransfer.CreateMoneyTransferDto;
import com.siarhei.jpatransactionaldemo.web.dto.moneytransfer.RetrieveMoneyTransferDto;
import com.siarhei.jpatransactionaldemo.web.mappers.MoneyTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MoneyTransferResource {

    private final MoneyTransferManagementService moneyTransferManagementService;
    private final MoneyTransferSearchService moneyTransferSearchService;
    private final MoneyTransferMapper moneyTransferMapper;

    @PostMapping("/money-transfers")
    public ResponseEntity<RetrieveMoneyTransferDto> createMoneyTransfer(@RequestBody CreateMoneyTransferDto moneyTransferDto) throws URISyntaxException {
        MoneyTransfer moneyTransfer = moneyTransferMapper.map(moneyTransferDto);
        MoneyTransfer result = moneyTransferManagementService.createMoneyTransfer(moneyTransfer);
        RetrieveMoneyTransferDto resultDto = moneyTransferMapper.map(result);
        return ResponseEntity.created(new URI("/money-transfers/" + result.getId()))
                .body(resultDto);
    }

    @GetMapping("/money-transfers")
    public ResponseEntity<List<RetrieveMoneyTransferDto>> getAllMoneyTransfers(MoneyTransferFilter filter) {
        List<MoneyTransfer> moneyTransfers = moneyTransferSearchService.findAll(filter);

        return ResponseEntity.ok(moneyTransfers.stream()
                .map(moneyTransferMapper::map)
                .collect(Collectors.toList()));
    }

    @GetMapping("/money-transfers/{id}")
    public ResponseEntity<RetrieveMoneyTransferDto> getMoneyTransfer(@PathVariable Long id) {
        MoneyTransfer moneyTransfer = moneyTransferSearchService.findOneOrThrow(id);
        return ResponseEntity.ok(moneyTransferMapper.map(moneyTransfer));
    }


}
