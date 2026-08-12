package com.alnwick.MonolithDocker.Dto;

public record TaskCreateDto(
        String title,
        String description,
        String status
) {
}
