package ru.practucum.explore.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.events.model.Event;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompilationEventDto {
    Event event;
    Compilation compilation;
}
