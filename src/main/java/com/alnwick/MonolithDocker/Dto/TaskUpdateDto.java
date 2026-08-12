package com.alnwick.MonolithDocker.Dto;

public record TaskUpdateDto(
        String description,
        String status
) {
}
