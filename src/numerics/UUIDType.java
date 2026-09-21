package numerics;

import java.util.function.Predicate;

public enum UUIDType {

    UNKNOWN(UUIDType::isOfUnknownType),

    MAC((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_1_BIT),

    SECURITY((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_2_BIT),
    MD5((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_3_BITS),

    RANDOM((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_4_BIT),

    SHA1((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_5_BITS),

    MAC_SORTABLE((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_6_BITS),

    RANDOM_SORTABLE((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_7_BITS),

    CUSTOM(((UUID uuid) ->
            (uuid.getHighBits() & Constants.VERSION_MASK)
                    == Constants.VERSION_8_BIT));

    private final Predicate<UUID> checker;

    static class Constants {

        static final int VERSION_MASK = 61440;

        static final long ALL_BUT_VERSION_BITS_MASK = -61441L;

        static final int VERSION_1_BIT = 4096;

        static final int VERSION_2_BIT = 8192;

        static final int VERSION_3_BITS = 12288;

        static final int VERSION_4_BIT = 16384;

        static final int VERSION_5_BITS = 20480;

        static final int VERSION_6_BITS = 24576;

        static final int VERSION_7_BITS = 28672;

        static final int VERSION_8_BIT = 32768;

    }

    private static boolean isOfUnknownType(UUID uuid) {
        long versionBits = uuid.getHighBits() & Constants.VERSION_MASK;
        return versionBits == 0 || versionBits > Constants.VERSION_8_BIT;
    }

    public boolean isOfType(UUID uuid) {
        return this.checker.test(uuid);
    }

    UUIDType(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
