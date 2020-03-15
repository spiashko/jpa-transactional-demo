package com.siarhei.jpatransactionaldemo.web.mappers;

import com.siarhei.jpatransactionaldemo.bankaccount.BankAccount;
import com.siarhei.jpatransactionaldemo.bankaccount.BankAccountCreationModel;
import com.siarhei.jpatransactionaldemo.web.dto.bankaccount.BankAccountCreationDto;
import com.siarhei.jpatransactionaldemo.web.dto.bankaccount.BankAccountViewADto;
import com.siarhei.jpatransactionaldemo.web.dto.bankaccount.BankAccountViewBDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountWebMapper {

    BankAccountViewADto mapToViewA(BankAccount entity);

    BankAccountViewBDto mapToViewB(BankAccount entity);

    BankAccountCreationModel map(BankAccountCreationDto dto);

}