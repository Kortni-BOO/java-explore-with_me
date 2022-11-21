package ru.practucum.explore.compilation.mapper;

import ru.practucum.explore.compilation.dto.CompilationEventDto;
import ru.practucum.explore.compilation.model.CompilationEvent;

public class CompEventMapper {
    public CompilationEvent toCompilationEvent(CompilationEventDto compilation) {
        return new CompilationEvent(
                null,
                compilation.getEvent(),
                compilation.getCompilation()
        );
    }
}
