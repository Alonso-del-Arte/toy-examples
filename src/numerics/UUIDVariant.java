package numerics;

import java.util.function.Predicate;

public enum UUIDVariant {

    NCS_BACKWARD_COMPATIBLE((UUID) -> false),

    DISTRIBUTED_COMPUTING_ENVIRONMENT((UUID) -> false),

    MICROSOFT_GUID((UUID) -> false),

    RESERVED_FOR_FUTURE_USE((UUID) -> false),

    UNKNOWN((UUID) -> false);

    private final Predicate<UUID> checker;

    // TODO: Write tests for this
    public boolean isOfVariant(UUID uuid) {
        return this.checker.test(uuid);
    }

    UUIDVariant(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
