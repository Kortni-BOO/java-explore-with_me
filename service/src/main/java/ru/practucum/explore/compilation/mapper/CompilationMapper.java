package ru.practucum.explore.compilation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practucum.explore.compilation.dto.CompilationDto;
import ru.practucum.explore.compilation.dto.NewCompilationDto;
import ru.practucum.explore.compilation.model.Compilation;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    public CompilationDto toCompilationDto(Compilation compilation) {
        CompilationDto compilationDto = CompilationDto.builder()
                .id(compilation.getId())
                .events(compilation.getEvents())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
        return compilationDto;
    }

    public Compilation toCompilation(CompilationDto compilationDto) {
        Compilation compilation = Compilation.builder()
                .id(compilationDto.getId())
                .events(compilationDto.getEvents())
                .pinned(compilationDto.getPinned())
                .title(compilationDto.getTitle())
                .build();

                return compilation;
    }

    public Compilation toCompilationFromNew(NewCompilationDto compilationDto) {
        Compilation compilation = Compilation.builder()
                .pinned(compilationDto.getPinned())
                .title(compilationDto.getTitle())
                .build();
        
        return compilation;
    }





}
