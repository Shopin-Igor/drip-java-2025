package model;

import annotations.*;

public record MyRecord(
        @NotNull
        @Min(10)
        @Max(50)
        Integer count,

        @NotNull
        String description
) {}
