package numerics;

import java.util.function.Predicate;

public enum UUIDType {

    UNKNOWN((UUID) -> false), MAC((UUID) -> false), SECURITY((UUID) -> false),
    MD5((UUID) -> false), RANDOM((UUID) -> false), SHA1((UUID) -> false),
    MAC_SORTABLE((UUID) -> false), RANDOM_SORTABLE((UUID) -> false),
    CUSTOM((UUID) -> false);

    private final Predicate<UUID> checker;

    // TODO: Write tests for this
    public boolean isOfType(UUID uuid) {
        return this.checker.test(uuid);
    }

    private UUIDType(Predicate<UUID> predicate) {
        this.checker = predicate;
    }

}
