package com.robinfood.paymentmethodsbc.utils;

import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

import com.robinfood.paymentmethodsbc.components.BaseApplicationContextAware;
import com.robinfood.paymentmethodsbc.dto.steps.StepGroupDTO;
import com.robinfood.paymentmethodsbc.dto.steps.StepGroupDTO.StepDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StepsControl {

    private StepsControl() {
    }

    public static void validateSteps(Object pipe, Iterable<StepGroupDTO> steps)
        throws PaymentStepException, EntityNotFoundException {
        log.info("All steps to execute: {}", steps);
        for (StepGroupDTO stepGroup : steps) {
            log.info(
                "Step group to execute: {}",
                stepGroup
                    .getSteps()
                    .stream()
                    .map((StepDTO item) -> item.getType().name())
                    .collect(Collectors.toList())
            );

            List<CompletableFuture<?>> results = executeSteps(pipe, stepGroup.getSteps());
            waitForResults(results);
        }
    }

    private static void waitForResults(List<CompletableFuture<?>> results)
        throws PaymentStepException, EntityNotFoundException {
        try {
            CompletableFuture
                .allOf(results.toArray(new CompletableFuture[0]))
                .join();
        } catch (CompletionException e) {
            if (e.getCause() instanceof BaseException) {
                throw (PaymentStepException) e.getCause();
            } else if (e.getCause() instanceof EntityNotFoundException) {
                throw (EntityNotFoundException) e.getCause();
            } 
            throw e;
        }
    }

    private static List<CompletableFuture<?>> executeSteps(
        Object pipe,
        List<StepGroupDTO.StepDTO> steps
    ) {
        return steps
            .stream()
            .map(
                step ->
                    CompletableFuture.runAsync(
                        () -> executeStepAsync(pipe, step)
                    )
            )
            .collect(Collectors.toList());
    }

    private static void executeStepAsync(
        Object pipe,
        StepGroupDTO.StepDTO step
    ) {
        final StepActions stepImpl = BaseApplicationContextAware
            .getApplicationContext()
            .getBean(step.getType().getClassImplement());

        try {
            log.info(
                "Step to execute {} with pipe: \n{}",
                step.getType(), convertToJson(pipe)
            );
            stepImpl.invoke(pipe);
        } catch (PaymentStepException | EntityNotFoundException e) {
            throw new CompletionException(e);
        }
    }
}
