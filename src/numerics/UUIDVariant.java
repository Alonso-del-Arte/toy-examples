package numerics;

import java.util.function.Predicate;

public enum UUIDVariant {

    NCS_BACKWARD_COMPATIBLE((UUID) -> false),

    DISTRIBUTED_COMPUTING_ENVIRONMENT((UUID) -> false),

    MICROSOFT_GUID((UUID) -> false),

    RESERVED_FOR_FUTURE_USE((UUID) -> false),

    UNKNOWN((UUID) -> false);

    private final Predicate<UUID> checker;

    static class Constants {
        
        // TODO: Write a test for this
        static final long HIGH_FOUR_BITS_MASK = 1152921504606846975L;

    }

    // TODO: Write tests for this
    public boolean isOfVariant(UUID uuid) {
        return this.checker.test(uuid);
    }

    UUIDVariant(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
