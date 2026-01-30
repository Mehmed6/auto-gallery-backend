package com.doganmehmet.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage()
    {
        var builder = new StringBuilder();

        builder.append(messageType.getMessage());

        if (ofStatic != null && !ofStatic.isEmpty()) {

            builder.append(" : ").append(ofStatic);
        }

        return builder.toString();
    }
}
