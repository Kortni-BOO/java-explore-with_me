package ru.practucum.explore.compilation.mapper;

import ru.practucum.explore.compilation.dto.CompilationEventDto;
import ru.practucum.explore.compilation.model.CompilationEvent;

public class CompEventMapper {

    public CompilationEvent toCompilationEvent(CompilationEventDto compilation) {
        CompilationEvent compilationEvent = CompilationEvent.builder()
                .event(compilation.getEvent())
                .compilation(compilation.getCompilation())
                .build();
        return compilationEvent;
    }
}
