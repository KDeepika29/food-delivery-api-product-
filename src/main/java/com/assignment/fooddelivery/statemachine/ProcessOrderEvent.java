package com.assignment.fooddelivery.statemachine;

import com.assignment.fooddelivery.enums.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrderEvent {
    @Autowired
    private StateMachineFactory<OrderStates, OrderEvents> stateMachineFactory;

    public void process(Long orderId, OrderEvents event, String remarks, UserTypes userTypes, Long enteredById) {
        // Get the state machine for the specific orderId
        StateMachine<OrderStates, OrderEvents> stateMachine = stateMachineFactory.getStateMachine(String.valueOf(orderId));
        stateMachine.getExtendedState().getVariables().put("orderId", orderId);
        stateMachine.getExtendedState().getVariables().put("remarks", remarks);
        stateMachine.getExtendedState().getVariables().put("enteredByType", userTypes.name());
        stateMachine.getExtendedState().getVariables().put("enteredById", enteredById);
        stateMachine.sendEvent(event);
    }
}
