package com.bstay.common.app.handler;

import lombok.Builder;

@Builder
public record ErrorDto(String errorCode, String errorMessage) {
}
