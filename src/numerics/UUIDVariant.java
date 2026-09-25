package numerics;

import java.util.function.Predicate;

public enum UUIDVariant {

    NCS_BACKWARD_COMPATIBLE((UUID uuid) -> uuid.getLowBits() > -1),

    DISTRIBUTED_COMPUTING_ENVIRONMENT(
            (UUID uuid) -> uuid.getLowBits() < Constants.MICROSOFT_GUID_C_LEVEL
    ),

    MICROSOFT_GUID(
            (UUID uuid)
                    -> (uuid.getLowBits() & Constants.HIGH_FOUR_BITS_MASK) < 0
    ),

    RESERVED_FOR_FUTURE_USE((UUID) -> false),

    UNKNOWN((UUID) -> false);

    private final Predicate<UUID> checker;

    static class Constants {
        
        static final long HIGH_FOUR_BITS_MASK = -1152921504606846976L;

        static final long VARIANT_INCREMENT = 1152921504606846976L;

        static final long MICROSOFT_GUID_C_LEVEL = -4611686018427387904L;

        static final long MICROSOFT_GUID_D_LEVEL = -3458764513820540928L;

    }

    // TODO: Write tests for this
    public boolean isOfVariant(UUID uuid) {
        return this.checker.test(uuid);
    }

    UUIDVariant(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
