package com.alnwick.MonolithDocker.Dto;

public record TaskListDto(
        Long id,
        String title,
        String status
) {
}
