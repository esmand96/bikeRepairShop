package se.kth.IV1350.bikerepairshop.model.domain;

/**
 * Represents the possible states a repair order can be in during its life cycle.
 */
public enum RepairOrderState {
    /** The order has been created but no diagnosis has been added yet. */
    NEWLY_CREATED,

    /** A diagnosis and repair tasks have been added, and the order is awaiting customer approval. */
    READY_FOR_APPROVAL,

    /** The customer has approved the order. */
    ACCEPTED,

    /** The customer has rejected the order. */
    REJECTED;
}