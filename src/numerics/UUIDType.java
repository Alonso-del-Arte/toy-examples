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

        /**
         * A bit pattern of all zeroes except for four one bits aligned with
         * version bits of a UUID. Use with the bitwise AND operator to isolate
         * the version bits from the high bits of a UUID. See also {@link
         * #ALL_BUT_VERSION_BITS_MASK}.
         */
        static final int VERSION_MASK = 61440;

        /**
         * A bit pattern of all ones except for four zero bits aligned with the
         * version bits of a UUID. Use with the bitwise AND operator to zero out
         * the version bits of a pseudorandom 64-bit number in order to replace
         * those bits with the desired version bits (generally 0100 for Version
         * 4). See also {@link #VERSION_MASK}.
         */
        static final long ALL_BUT_VERSION_BITS_MASK = -61441;

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
