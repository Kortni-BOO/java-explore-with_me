package ru.practucum.explore.compilation.dto;

import lombok.*;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.events.model.Event;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationEventDto {
    private Event event;
    private Compilation compilation;
}
