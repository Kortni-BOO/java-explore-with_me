package ru.practucum.explore.compilation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practucum.explore.compilation.dto.CompilationDto;
import ru.practucum.explore.compilation.dto.NewCompilationDto;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.events.mapper.EventMapper;

@Component
@RequiredArgsConstructor
public class CompilationMapper {
    EventMapper mapper;

    public CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(
                compilation.getId(),
                compilation.getEvents(),
                compilation.getPinned(),
                compilation.getTitle()
        );
    }

    public Compilation toCompilation(CompilationDto compilationDto) {
        return new Compilation(
                compilationDto.getId(),
                compilationDto.getEvents(),
                compilationDto.getPinned(),
                compilationDto.getTitle()
        );
    }

    public Compilation toCompilationFromNew(NewCompilationDto compilationDto) {
        return new Compilation(
                0,
                null,
                compilationDto.getPinned(),
                compilationDto.getTitle()
        );
    }





}
