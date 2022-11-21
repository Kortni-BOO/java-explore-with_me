package ru.practucum.explore.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Static {
    private String app;
    private String url;
    private String ip;
    private LocalDateTime time;
}
