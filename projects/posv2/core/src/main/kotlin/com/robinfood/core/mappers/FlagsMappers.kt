package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.CorporateFlagDTO
import com.robinfood.core.dtos.transactionrequest.FlagsDTO
import com.robinfood.core.dtos.transactionrequest.PitsFlagDTO
import com.robinfood.core.dtos.transactionrequest.SubmarineFlagDTO
import com.robinfood.core.dtos.transactionrequest.TogoFlagDTO
import com.robinfood.core.entities.transactionrequest.CorporateFlagEntity
import com.robinfood.core.entities.transactionrequest.FlagsEntity
import com.robinfood.core.entities.transactionrequest.PitsFlagEntity
import com.robinfood.core.entities.transactionrequest.SubmarineFlagEntity
import com.robinfood.core.entities.transactionrequest.TogoFlagEntity

fun FlagsDTO.toFlagsEntity(): FlagsEntity {
    return FlagsEntity(
        corporate = corporate?.toCorporateFlagEntity(),
        pits = pits?.toPitsFlagEntity(),
        submarine = submarine?.toSubmarineFlagEntity(),
        togo = togo?.toTogoFlagEntity()
    )
}

fun CorporateFlagDTO.toCorporateFlagEntity(): CorporateFlagEntity {
    return CorporateFlagEntity(
        isActive = isActive
    )
}

fun PitsFlagDTO.toPitsFlagEntity(): PitsFlagEntity {
    return PitsFlagEntity(
        carPlate = carPlate,
        isActive = isActive
    )
}

fun SubmarineFlagDTO.toSubmarineFlagEntity(): SubmarineFlagEntity {
    return SubmarineFlagEntity(
        isActive = isActive
    )
}

fun TogoFlagDTO.toTogoFlagEntity(): TogoFlagEntity {
    return TogoFlagEntity(
        isActive = isActive,
        statusId = statusId
    )
}