package numerics;

import java.util.function.Predicate;

public enum UUIDType {

    UNKNOWN((UUID) -> false),

    MAC((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_1_BIT),

    SECURITY((UUID) -> false),
    MD5((UUID) -> false),

    RANDOM((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_4_BIT),

    SHA1((UUID) -> false),
    MAC_SORTABLE((UUID) -> false), RANDOM_SORTABLE((UUID) -> false),
    CUSTOM((UUID) -> false);

    private final Predicate<UUID> checker;

    private static class Constants {

        private static final int VERSION_MASK = 61440;

        private static final int VERSION_1_BIT = 4096;

        private static final int VERSION_4_BIT = 16384;

    }

    // TODO: Write tests for this
    public boolean isOfType(UUID uuid) {
        return this.checker.test(uuid);
    }

    UUIDType(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
